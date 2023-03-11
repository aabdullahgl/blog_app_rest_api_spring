package com.egitim.blogapi.service.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

	@NotNull(message = "username field cannot be empty")
	@NotBlank(message = "username field cannot be empty")
	private String userName;

	@NotNull(message = "mail field cannot be empty")
	@NotBlank(message = "mail field cannot be empty")
	@Email(message = "invalid format")
	private String userMail;

	@NotNull(message = "password field cannot be empty")
	@NotBlank(message = "password field cannot be empty")
	@Size(min = 6, message = "password must be at least 6 characters!")
	private String password;

	private String userAbout;
}
