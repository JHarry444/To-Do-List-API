package com.qa.todo.service;

import org.springframework.stereotype.Service;

import com.qa.todo.dto.TaskDto;
import com.qa.todo.exception.TaskNotFoundException;
import com.qa.todo.persistence.domain.TaskEntity;
import com.qa.todo.persistence.repo.TaskRepo;
import com.qa.todo.util.TaskMapper;

@Service
public class TaskService {

	private TaskRepo repo;

	private TaskMapper mapper;

	public TaskService(TaskRepo repo, TaskMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public TaskDto addTask(TaskDto task) {
		TaskEntity created = this.repo.save(this.mapper.mapToEntity(task));
		return this.mapper.mapToDto(created);
	}

	public TaskDto getTask(Long id) {
		TaskEntity TaskEntity = this.repo.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("Unable to find a Task with an id of " + id));
		return this.mapper.mapToDto(TaskEntity);
	}

	public TaskDto updateTask(TaskDto Task, Long id) {
		TaskEntity newTaskEntity = this.mapper.map(Task, TaskEntity.class);
		TaskEntity entityToUpdate = this.repo.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("Unable to find a Task with an id of " + id));
		entityToUpdate.setCompleted(newTaskEntity.isCompleted());
		entityToUpdate.setDescription(newTaskEntity.getDescription());
		return this.mapper.mapToDto(this.repo.save(entityToUpdate));
	}

	public boolean removeTask(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
