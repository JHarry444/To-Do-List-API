package com.qa.todo.util;

import com.qa.todo.dto.TaskDto;
import com.qa.todo.persistence.domain.ListEntity;
import com.qa.todo.persistence.domain.TaskEntity;

public class TaskMapper extends BaseMapper {

	public TaskMapper() {
		super();
	}

	public TaskDto mapToDto(TaskEntity entity) {
		TaskDto dto = new TaskDto(entity.getId(), entity.getDescription(), entity.isCompleted(),
				entity.getList().getId());
		return dto;
	}

	public TaskEntity mapToEntity(TaskDto dto) {
		ListEntity list = new ListEntity();
		list.setId(dto.getListId());
		TaskEntity entity = new TaskEntity(dto.getId(), dto.getDescription(), dto.isCompleted(), list);
		return entity;
	}
}
