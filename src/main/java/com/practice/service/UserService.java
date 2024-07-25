package com.practice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.request.dto.UserRequestDTO;
import com.practice.response.dto.UserResponseDTO;
import com.practice.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponseDTO>> registerUser(UserRequestDTO userRequestDTO);

	public ResponseEntity<ResponseStructure<UserResponseDTO>> findUserById(int id);

	public ResponseEntity<ResponseStructure<UserResponseDTO>> updateUserById(int id, UserRequestDTO userRequestDTO);

	public ResponseEntity<ResponseStructure<UserResponseDTO>> deleteUserById(int id);

	public ResponseEntity<ResponseStructure<List<UserResponseDTO>>> findAllUsers();
}