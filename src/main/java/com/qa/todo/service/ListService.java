package com.qa.todo.service;

import org.springframework.stereotype.Service;

import com.qa.todo.dto.ListDto;
import com.qa.todo.exception.ListNotFoundException;
import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.repo.ListRepo;
import com.qa.todo.util.ListMapper;

@Service
public class ListService {

	private ListRepo repo;

	private ListMapper mapper;

	public ListService(ListRepo repo, ListMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public ListDto addList(ListDto list) {
		ListEntity created = this.repo.save(this.mapper.map(list, ListEntity.class));
		return this.mapper.mapToDto(created);
	}

	public ListDto getList(Long id) {
		ListEntity listEntity = this.repo.findById(id)
				.orElseThrow(() -> new ListNotFoundException("Unable to find a list with an id of " + id));
		return this.mapper.mapToDto(listEntity);
	}

	public ListDto updateList(ListDto list, Long id) {
		ListEntity newListEntity = this.mapper.map(list, ListEntity.class);
		ListEntity entityToUpdate = this.repo.findById(id)
				.orElseThrow(() -> new ListNotFoundException("Unable to find a list with an id of " + id));
		entityToUpdate.setTitle(newListEntity.getTitle());
		return this.mapper.mapToDto(entityToUpdate);
	}

	public boolean removeList(Long id) {
		ListEntity listToRemove = this.repo.findById(id).orElseThrow(() -> new ListNotFoundException());
		this.repo.delete(listToRemove);
		return this.repo.existsById(id);
	}

}
