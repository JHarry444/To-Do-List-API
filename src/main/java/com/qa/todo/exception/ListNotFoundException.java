package com.qa.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No such List exists")
public class ListNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3439220795792961733L;

	public ListNotFoundException() {
		super();
	}

	public ListNotFoundException(String Message) {
		super(Message);
	}

}
