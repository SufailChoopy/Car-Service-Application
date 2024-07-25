package com.practice.servimpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.entity.CarService;
import com.practice.entity.User;
import com.practice.exception.CarServiceIdNotFoundException;
import com.practice.exception.UserIdNotFoundException;
import com.practice.mapper.UserMapper;
import com.practice.repository.UserRepository;
import com.practice.request.dto.CarServiceRequestDTO;
import com.practice.request.dto.UserRequestDTO;
import com.practice.response.dto.CarServiceResponseDTO;
import com.practice.response.dto.UserResponseDTO;
import com.practice.service.UserService;
import com.practice.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	// Instead of using @Autowired 
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDTO>> registerUser(UserRequestDTO userRequestDTO) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseStructure<UserResponseDTO>()
						.setStatusCode(HttpStatus.CREATED.value())
						.setMessage("User Object Created Successfully!")
						.setData(userMapper.mapToUserResponse(userRepository.save(userMapper.mapToUser(userRequestDTO)))));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDTO>> findUserById(int id) {
		return userRepository.findById(id)   
				.map(userService -> ResponseEntity        
						.status(HttpStatus.FOUND)
						.body(new ResponseStructure<UserResponseDTO>()
								.setStatusCode(HttpStatus.FOUND.value())
								.setData(userMapper.mapToUserResponse(userService))	
								.setMessage("User Found!")))
				.orElseThrow(() -> new UserIdNotFoundException("User Not Found!"));  

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDTO>> updateUserById(int id, UserRequestDTO updatedUserRequestDTO) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
						
		return	userRepository.findByEmail(email)
				.map(existingUser -> {
					User user = userMapper.mapToUser(updatedUserRequestDTO);
					user.setId(existingUser.getId());
					UserResponseDTO  userResponseDTO = userMapper.mapToUserResponse(userRepository.save(user));
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseStructure<UserResponseDTO>()
									.setStatusCode(HttpStatus.OK.value())
									.setMessage("User Data Updated !")
									.setData(userResponseDTO));
				}).orElseThrow(()-> new UserIdNotFoundException("User ID Not Found !"));

	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponseDTO>> deleteUserById(int id) {
		return	userRepository.findById(id)
				.map(user -> { userRepository.delete(user);
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(new ResponseStructure<UserResponseDTO>()
								.setStatusCode(HttpStatus.OK.value())
								.setData(userMapper.mapToUserResponse(user))
								.setMessage("User Deleted !"));
				})
				.orElseThrow(() -> new UserIdNotFoundException("User ID Not Found !"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponseDTO>>> findAllUsers() {
		List<UserResponseDTO> userResponseDTOList = userRepository.findAll()
				.stream()
				.map(user -> userMapper.mapToUserResponse(user))
				.toList();
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<UserResponseDTO>>()
						.setStatusCode(HttpStatus.OK.value())
						.setMessage("List of Users Found !")
						.setData(userResponseDTOList));
	}
}
