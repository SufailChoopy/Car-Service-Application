package com.practice.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practice.exception.UserIdNotFoundException;
import com.practice.exception.CarIdNotFoundException;
import com.practice.exception.CarServiceIdNotFoundException;

@RestControllerAdvice
public class ApplicationHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> carIdNotFoundException(CarIdNotFoundException ce) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setData(ce.getMessage());
		es.setMessage("Car with the given ID not Found");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> CarServiceIdNotFoundException(CarServiceIdNotFoundException se) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setData(se.getMessage());
		es.setMessage("Service with the given ID not Found");

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> UserIdNotFoundException(UserIdNotFoundException  ue) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		es.setStatusCode(HttpStatus.NOT_FOUND.value());
		es.setData(ue.getMessage());
		es.setMessage("User with the given ID not Found");
		return new ResponseEntity<ErrorStructure<String>>(es,HttpStatus.NOT_FOUND);
		
	}
}
