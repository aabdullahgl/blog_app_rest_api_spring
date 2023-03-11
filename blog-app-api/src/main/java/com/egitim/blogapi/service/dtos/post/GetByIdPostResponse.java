package com.egitim.blogapi.service.dtos.post;

import java.util.Date;

import com.egitim.blogapi.service.dtos.category.CategoryDtoResponse;
import com.egitim.blogapi.service.dtos.user.UserDtoResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetByIdPostResponse {

	private int postId;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDtoResponse categoryDtoResponse;

	private UserDtoResponse userDtoResponse;
}
