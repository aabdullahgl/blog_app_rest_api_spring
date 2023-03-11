package com.egitim.blogapi.service;

import java.util.List;

import com.egitim.blogapi.service.dtos.post.CreatePostRequest;
import com.egitim.blogapi.service.dtos.post.GetByIdPostResponse;
import com.egitim.blogapi.service.dtos.post.PostDtoResponse;
import com.egitim.blogapi.service.dtos.post.PostPageResponse;
import com.egitim.blogapi.service.dtos.post.UpdatePostRequest;

public interface PostService {

	public PostDtoResponse createPost(CreatePostRequest createPostRequest, int userId, int categoryId);

	public PostDtoResponse updatePost(UpdatePostRequest updatePostRequest, int postId);

	public void deletePost(int postId);

	public PostPageResponse getAllPostPageResponse(int pageNumber, int pageSize, String sortBy,String sortDirection);

	public GetByIdPostResponse getByIdPostResponse(int postId);

	public List<PostDtoResponse> getPostsByCategory(int categoryId);

	public List<PostDtoResponse> getPostsByUser(int userId);

	public List<PostDtoResponse> searchPost(String title);

}
