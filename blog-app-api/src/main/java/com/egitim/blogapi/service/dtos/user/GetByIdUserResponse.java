package com.egitim.blogapi.service.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdUserResponse {

	private int userId;

	private String userName;

	private String userMail;

	private String password;

	private String userAbout;
}
