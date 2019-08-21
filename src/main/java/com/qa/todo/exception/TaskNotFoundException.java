package com.qa.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such Task exists")
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
