package com.qa.todo.service;

import org.springframework.stereotype.Service;

import com.qa.todo.dto.TaskDto;
import com.qa.todo.exception.TaskNotFoundException;
import com.qa.todo.persistence.domain.TaskEntity;
import com.qa.todo.persistence.repo.TaskRepo;
import com.qa.todo.util.BaseMapper;

@Service
public class TaskService {

	private TaskRepo repo;

	private BaseMapper mapper;

	public TaskService(TaskRepo repo, BaseMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public TaskDto addTask(TaskDto Task) {
		TaskEntity created = this.repo.save(this.mapper.map(Task, TaskEntity.class));
		return this.mapper.map(created, TaskDto.class);
	}

	public TaskDto getTask(Long id) {
		TaskEntity TaskEntity = this.repo.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("Unable to find a Task with an id of " + id));
		return this.mapper.map(TaskEntity, TaskDto.class);
	}

	public TaskDto updateTask(TaskDto Task, Long id) {
		TaskEntity newTaskEntity = this.mapper.map(Task, TaskEntity.class);
		TaskEntity entityToUpdate = this.repo.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("Unable to find a Task with an id of " + id));
		entityToUpdate.setCompleted(newTaskEntity.isCompleted());
		entityToUpdate.setDescription(newTaskEntity.getDescription());
		return this.mapper.map(this.repo.save(entityToUpdate), TaskDto.class);
	}

	public boolean removeTask(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
