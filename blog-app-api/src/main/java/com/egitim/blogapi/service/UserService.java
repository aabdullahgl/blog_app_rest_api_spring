package com.egitim.blogapi.service;

import java.util.List;

import com.egitim.blogapi.service.dtos.user.CreateUserRequest;
import com.egitim.blogapi.service.dtos.user.GetByIdUserResponse;
import com.egitim.blogapi.service.dtos.user.UpdateUserRequest;
import com.egitim.blogapi.service.dtos.user.UserDtoResponse;

public interface UserService {

	public UserDtoResponse createUser(CreateUserRequest createUserRequest);

	public UserDtoResponse updateUser(UpdateUserRequest updateUserRequest, int userId);

	public void deleteUser(int userId);

	public GetByIdUserResponse getByIdUser(int userId);

	public List<UserDtoResponse> getAllUserResponses();
}
