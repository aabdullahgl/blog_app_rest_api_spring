package com.egitim.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.egitim.blogapi.core.exception.ResourceNotFoundException;
import com.egitim.blogapi.core.mapper.ModelMapperService;
import com.egitim.blogapi.model.User;
import com.egitim.blogapi.repository.UserRepository;
import com.egitim.blogapi.service.UserService;
import com.egitim.blogapi.service.dtos.user.CreateUserRequest;
import com.egitim.blogapi.service.dtos.user.GetByIdUserResponse;
import com.egitim.blogapi.service.dtos.user.UpdateUserRequest;
import com.egitim.blogapi.service.dtos.user.UserDtoResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapperService modelMapperService;

	@Override
	public UserDtoResponse createUser(CreateUserRequest createUserRequest) {
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		User createdUser = this.userRepository.save(user);
		return this.modelMapperService.forResponse().map(createdUser, UserDtoResponse.class);
	}

	@Override
	public UserDtoResponse updateUser(UpdateUserRequest updateUserRequest, int userId) {
		User userIdExist = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " User Id", userId));

		this.modelMapperService.forRequest().map(updateUserRequest, userIdExist);
		User user = this.userRepository.save(userIdExist);
		return this.modelMapperService.forResponse().map(user, UserDtoResponse.class);
	}

	@Override
	public void deleteUser(int userId) {
		User userIdExist = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " User Id", userId));
		this.userRepository.delete(userIdExist);
	}

	@Override
	public GetByIdUserResponse getByIdUser(int userId) {
		User userIdExist = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " User Id", userId));
		return this.modelMapperService.forResponse().map(userIdExist, GetByIdUserResponse.class);
	}

	@Override
	public List<UserDtoResponse> getAllUserResponses() {
		List<User> listUser = this.userRepository.findAll();

		List<UserDtoResponse> userDtoResponses = listUser.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, UserDtoResponse.class))
				.collect(Collectors.toList());
		return userDtoResponses;
	}

}
