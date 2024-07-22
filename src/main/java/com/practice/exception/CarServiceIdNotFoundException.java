package com.practice.exception;

public class CarServiceIdNotFoundException extends RuntimeException {

	private String message;

	public CarServiceIdNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
