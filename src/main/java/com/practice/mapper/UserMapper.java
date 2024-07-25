package com.practice.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.practice.entity.User;
import com.practice.request.dto.UserRequestDTO;
import com.practice.response.dto.UserResponseDTO;

@Component
public class UserMapper {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User mapToUser(UserRequestDTO userRequestDTO) {

		User user = new User();
		user.setName(userRequestDTO.getName());
		user.setEmail(userRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		user.setUserRole(userRequestDTO.getUserRole());
		return user;
	}

	public UserResponseDTO mapToUserResponse(User user) {

		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(user.getId());
		userResponseDTO.setName(user.getName());
		userResponseDTO.setEmail(user.getEmail());
		userResponseDTO.setUserRole(user.getUserRole());
		return userResponseDTO;

	}

}