package com.practice.exception;

public class CarIdNotFoundException extends RuntimeException {

	private String message;

	public CarIdNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
