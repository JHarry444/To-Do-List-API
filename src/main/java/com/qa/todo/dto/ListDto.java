package com.qa.todo.dto;

import java.util.Set;

public class ListDto {
	private Long id;

	private String title = null;

	private Set<Long> tasks;

	public ListDto(Long id, String title, Set<Long> tasks) {
		super();
		this.id = id;
		this.title = title;
		this.setTasks(tasks);
	}

	public ListDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListDto other = (ListDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Long> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Long> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "ListDto [id=" + id + ", title=" + title + ", tasks=" + tasks + "]";
	}

}