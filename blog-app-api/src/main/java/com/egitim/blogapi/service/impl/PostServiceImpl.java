package com.egitim.blogapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.egitim.blogapi.core.exception.ResourceNotFoundException;
import com.egitim.blogapi.core.mapper.ModelMapperService;
import com.egitim.blogapi.model.Category;
import com.egitim.blogapi.model.Post;
import com.egitim.blogapi.model.User;
import com.egitim.blogapi.repository.CategoryRepository;
import com.egitim.blogapi.repository.PostRepository;
import com.egitim.blogapi.repository.UserRepository;
import com.egitim.blogapi.service.PostService;
import com.egitim.blogapi.service.dtos.post.CreatePostRequest;
import com.egitim.blogapi.service.dtos.post.GetByIdPostResponse;
import com.egitim.blogapi.service.dtos.post.PostDtoResponse;
import com.egitim.blogapi.service.dtos.post.PostPageResponse;
import com.egitim.blogapi.service.dtos.post.UpdatePostRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final ModelMapperService modelMapperService;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public PostDtoResponse createPost(CreatePostRequest createPostRequest, int userId, int categoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " User Id", userId));

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", categoryId));

		Post post = this.modelMapperService.forRequest().map(createPostRequest, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepository.save(post);

		return this.modelMapperService.forResponse().map(savedPost, PostDtoResponse.class);
	}

	@Override
	public PostDtoResponse updatePost(UpdatePostRequest updatePostRequest, int postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", " Post Id", postId));
		this.modelMapperService.forRequest().map(updatePostRequest, post);
		Post updatedPost = this.postRepository.save(post);
		return this.modelMapperService.forResponse().map(updatedPost, PostDtoResponse.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", " Post Id", postId));
		this.postRepository.delete(post);
	}

	@Override
	public PostPageResponse getAllPostPageResponse(int pageNumber, int pageSize, String sortBy, String sortDirection) {

		pageNumber -= 1;

		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepository.findAll(pageable);
		List<Post> allPosts = pagePost.getContent();
		List<PostDtoResponse> postDtoResponses = allPosts.stream()
				.map(post -> this.modelMapperService.forResponse().map(post, PostDtoResponse.class))
				.collect(Collectors.toList());

		PostPageResponse postPageResponse = new PostPageResponse();
		postPageResponse.setContent(postDtoResponses);
		postPageResponse.setPageNumber(pagePost.getNumber() + 1);
		postPageResponse.setPageSize(pagePost.getSize());
		postPageResponse.setTotalElements(pagePost.getTotalElements());
		postPageResponse.setTotalPages(pagePost.getTotalPages());
		postPageResponse.setLastPage(pagePost.isLast()); //not setup

		return postPageResponse;
	}

	@Override
	public GetByIdPostResponse getByIdPostResponse(int postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", " Post Id", postId));

		return this.modelMapperService.forResponse().map(post, GetByIdPostResponse.class);

	}

	@Override
	public List<PostDtoResponse> getPostsByCategory(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", categoryId));
		List<Post> posts = this.postRepository.findByCategory(category);
		List<PostDtoResponse> postDtoResponses = posts.stream()
				.map(post -> this.modelMapperService.forResponse().map(post, PostDtoResponse.class))
				.collect(Collectors.toList());
		return postDtoResponses;
	}

	@Override
	public List<PostDtoResponse> getPostsByUser(int userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", " User Id", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDtoResponse> postDtoResponses = posts.stream()
				.map(post -> this.modelMapperService.forResponse().map(post, PostDtoResponse.class))
				.collect(Collectors.toList());
		return postDtoResponses;
	}

	@Override
	public List<PostDtoResponse> searchPost(String title) {
		List<Post> posts = this.postRepository.findByTitleContaining("%"+title+"%");
		List<PostDtoResponse> postDtoResponses = posts.stream()
				.map(post -> this.modelMapperService.forResponse().map(post, PostDtoResponse.class))
				.collect(Collectors.toList());
		return postDtoResponses;
	}

}
