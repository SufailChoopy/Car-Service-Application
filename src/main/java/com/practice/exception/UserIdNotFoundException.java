package com.practice.exception;

public class UserIdNotFoundException extends RuntimeException {

	private String message;


	public UserIdNotFoundException(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
}
