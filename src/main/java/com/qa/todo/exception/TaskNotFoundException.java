package com.qa.todo.exception;

public class TaskNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3439220795792961733L;

	public TaskNotFoundException() {
		super();
	}

	public TaskNotFoundException(String Message) {
		super(Message);
	}

}
