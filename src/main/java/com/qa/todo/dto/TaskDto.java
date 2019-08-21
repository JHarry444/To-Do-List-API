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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDto other = (TaskDto) obj;
		if (completed != other.completed)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listId == null) {
			if (other.listId != null)
				return false;
		} else if (!listId.equals(other.listId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", description=" + description + ", completed=" + completed + ", listId=" + listId
				+ "]";
	}

}
