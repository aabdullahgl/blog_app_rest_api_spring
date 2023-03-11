package com.egitim.blogapi.service.dtos.jwt;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String userName;
	private String password;
}
