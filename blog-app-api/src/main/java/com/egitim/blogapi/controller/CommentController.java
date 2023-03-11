package com.egitim.blogapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.blogapi.core.ApiResponse;
import com.egitim.blogapi.service.CommentService;
import com.egitim.blogapi.service.dtos.comment.CommentDtoResponse;
import com.egitim.blogapi.service.dtos.comment.CreateCommentRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDtoResponse> createComment(@RequestBody CreateCommentRequest createCommentRequest,
			@PathVariable int postId) {
		CommentDtoResponse dtoResponse = this.commentService.createComment(createCommentRequest, postId);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable int commentId) {
		this.commentService.delete(commentId);
		return new ResponseEntity<>(new ApiResponse("Comment deleted succesfully !!", true), HttpStatus.OK);
	}
}
