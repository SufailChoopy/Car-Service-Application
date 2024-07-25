package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.request.dto.CarServiceRequestDTO;
import com.practice.request.dto.UserRequestDTO;
import com.practice.response.dto.CarServiceResponseDTO;
import com.practice.response.dto.UserResponseDTO;
import com.practice.service.UserService;
import com.practice.utility.ResponseStructure;

//@RequestMapping("/users") --> common name for url
@RestController
public class UserController {

	// Instead of @Autowired private UserService userService;
	
	// private final UserServiceImpl userServiceImpl;
	//
	// public UserController(UserServiceImpl userServiceImpl) {
	// this.userServiceImpl = userServiceImpl;
	// }

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<UserResponseDTO>> registrUser(@RequestBody UserRequestDTO userRequestDTO) {
		return userService.registerUser(userRequestDTO);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<UserResponseDTO>> findUser(@PathVariable int id) {
		return userService.findUserById(id);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<UserResponseDTO>> updateUserById(@PathVariable int id,
			@RequestBody UserRequestDTO userRequestDTO) {
		return userService.updateUserById(id, userRequestDTO);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<UserResponseDTO>> deleteUserById(@PathVariable int id) {
		return userService.deleteUserById(id);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponseDTO>>> findAllUsers() {
		return userService.findAllUsers();
	}
}