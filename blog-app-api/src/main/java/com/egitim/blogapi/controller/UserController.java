package com.egitim.blogapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.blogapi.service.UserService;
import com.egitim.blogapi.service.dtos.user.CreateUserRequest;
import com.egitim.blogapi.service.dtos.user.GetByIdUserResponse;
import com.egitim.blogapi.service.dtos.user.UpdateUserRequest;
import com.egitim.blogapi.service.dtos.user.UserDtoResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDtoResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		UserDtoResponse createdUserResponse = this.userService.createUser(createUserRequest);
		return new ResponseEntity<UserDtoResponse>(createdUserResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{userId}")
	public ResponseEntity<UserDtoResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest,
			@PathVariable int userId) {
		UserDtoResponse updatedUserResponse = this.userService.updateUser(updateUserRequest, userId);
		return new ResponseEntity<UserDtoResponse>(updatedUserResponse, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<String>("Kulanıcı silindi", HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	@ResponseStatus(code = HttpStatus.OK)
	public GetByIdUserResponse getByIdUser(@PathVariable int userId) {
		return this.userService.getByIdUser(userId);
	}

	@GetMapping
	public List<UserDtoResponse> getAllUserResponses() {
		return this.userService.getAllUserResponses();
	}
}
