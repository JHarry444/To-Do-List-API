package com.qa.todo.util;

import java.util.stream.Collectors;

import com.qa.todo.dto.ListDto;
import com.qa.todo.persistence.domain.ListEntity;

public class ListMapper extends BaseMapper {

	public ListMapper() {
		super();
	}

	public ListDto mapToDto(ListEntity entity) {
		ListDto dto = new ListDto(entity.getId(), entity.getTitle(),
				entity.getTasks().stream().map(t -> t.getId()).collect(Collectors.toSet()));
		return dto;
	}

	public ListEntity mapToEntity(ListDto dto) {
		ListEntity entity = new ListEntity(dto.getId());
		return entity;
	}
}