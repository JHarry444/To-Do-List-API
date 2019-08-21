package com.qa.todo.dto;

public class TaskDto {

	private Long id;

	private String description;

	private boolean completed;

	private Long listId;

	public TaskDto(Long id, String description, boolean completed, Long listId) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
		this.listId = listId;
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

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

}
