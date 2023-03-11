package com.egitim.blogapi.service.dtos.post;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.egitim.blogapi.service.dtos.category.CategoryDtoResponse;
import com.egitim.blogapi.service.dtos.comment.CommentDtoResponse;
import com.egitim.blogapi.service.dtos.user.UserDtoResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDtoResponse {

	private int postId;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDtoResponse categoryDtoResponse;

	private UserDtoResponse userDtoResponse;

	private Set<CommentDtoResponse> comments = new HashSet<>();
}
