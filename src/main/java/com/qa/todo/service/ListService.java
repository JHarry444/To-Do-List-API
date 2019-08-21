package com.qa.todo.service;

import org.springframework.stereotype.Service;

import com.qa.todo.dto.ListDto;
import com.qa.todo.exception.ListNotFoundException;
import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.repo.ListRepo;
import com.qa.todo.util.BaseMapper;

@Service
public class ListService {

	private ListRepo repo;

	private BaseMapper mapper;

	public ListService(ListRepo repo, BaseMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public ListEntity addList(ListDto list) {
		return this.repo.save(this.mapper.map(list, ListEntity.class));
	}

	public ListDto getList(Long id) {
		ListEntity listEntity = this.repo.findById(id)
				.orElseThrow(() -> new ListNotFoundException("Unable to find a list with an id of " + id));
		return this.mapper.map(listEntity, ListDto.class);
	}

	public ListEntity updateList(ListDto list, Long id) {
		ListEntity newListEntity = this.mapper.map(list, ListEntity.class);
		ListEntity entityToUpdate = this.repo.findById(id)
				.orElseThrow(() -> new ListNotFoundException("Unable to find a list with an id of " + id));
		entityToUpdate.setTitle(newListEntity.getTitle());
		entityToUpdate.setTasks(newListEntity.getTasks());
		return this.repo.save(entityToUpdate);
	}

	public boolean removeList(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
