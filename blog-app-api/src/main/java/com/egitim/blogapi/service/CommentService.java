package com.egitim.blogapi.service;

import com.egitim.blogapi.service.dtos.comment.CommentDtoResponse;
import com.egitim.blogapi.service.dtos.comment.CreateCommentRequest;

public interface CommentService {

	public CommentDtoResponse createComment(CreateCommentRequest createCommentRequest, int postID);

	public void delete(int commentId);
}
