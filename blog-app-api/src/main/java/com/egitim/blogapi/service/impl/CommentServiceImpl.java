package com.egitim.blogapi.service.impl;

import org.springframework.stereotype.Service;

import com.egitim.blogapi.core.exception.ResourceNotFoundException;
import com.egitim.blogapi.core.mapper.ModelMapperService;
import com.egitim.blogapi.model.Comment;
import com.egitim.blogapi.model.Post;
import com.egitim.blogapi.repository.CommentRepository;
import com.egitim.blogapi.repository.PostRepository;
import com.egitim.blogapi.service.CommentService;
import com.egitim.blogapi.service.dtos.comment.CommentDtoResponse;
import com.egitim.blogapi.service.dtos.comment.CreateCommentRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final ModelMapperService modelMapperService;

	@Override
	public CommentDtoResponse createComment(CreateCommentRequest createCommentRequest, int postID) {
		Post post = this.postRepository.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postID));
		Comment comment = this.modelMapperService.forRequest().map(createCommentRequest, Comment.class);

		comment.setPost(post);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapperService.forResponse().map(savedComment, CommentDtoResponse.class);
	}

	@Override
	public void delete(int commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepository.delete(comment);
	}

}
