package com.qa.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskDto {

	private Long id;

	private String description;

	private boolean completed;

	@JsonIgnore
	private ListDto list;

	public TaskDto(Long id, String description, boolean completed) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
	}

	public TaskDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public ListDto getList() {
		return list;
	}

	public void setList(ListDto list) {
		this.list = list;
	}

}
