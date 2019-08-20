package com.qa.todo.exception;

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
