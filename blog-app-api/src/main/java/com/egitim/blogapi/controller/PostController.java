package com.egitim.blogapi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.egitim.blogapi.config.AppConstants;
import com.egitim.blogapi.core.ApiResponse;
import com.egitim.blogapi.core.mapper.ModelMapperService;
import com.egitim.blogapi.service.FileService;
import com.egitim.blogapi.service.PostService;
import com.egitim.blogapi.service.dtos.post.CreatePostRequest;
import com.egitim.blogapi.service.dtos.post.GetByIdPostResponse;
import com.egitim.blogapi.service.dtos.post.PostDtoResponse;
import com.egitim.blogapi.service.dtos.post.PostPageResponse;
import com.egitim.blogapi.service.dtos.post.UpdatePostRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	private final FileService fileService;

	private final ModelMapperService modelMapperService;

	@Value(AppConstants.PROJECT_IMAGE_PATH)
	private String path;

	@PostMapping(path = "/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDtoResponse> createPost(@RequestBody CreatePostRequest createPostRequest,
			@PathVariable int userId, @PathVariable int categoryId) {
		PostDtoResponse newResponse = this.postService.createPost(createPostRequest, userId, categoryId);
		return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/posts/{postId}")
	public ResponseEntity<PostDtoResponse> updatePost(@RequestBody UpdatePostRequest updatePostRequest,
			@PathVariable int postId) {
		PostDtoResponse newResponse = this.postService.updatePost(updatePostRequest, postId);
		return new ResponseEntity<>(newResponse, HttpStatus.OK);
	}

	@DeleteMapping(path = "/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
		return ResponseEntity.ok(new ApiResponse("Post is successfully deleted", true));
	}

	@GetMapping(path = "/posts")
	public ResponseEntity<PostPageResponse> getAllPostResponses(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection) {
		return ResponseEntity.ok(this.postService.getAllPostPageResponse(pageNumber, pageSize, sortBy, sortDirection));
	}

	@GetMapping(path = "/posts/{postId}")
	public ResponseEntity<GetByIdPostResponse> getByIdPostResponse(@PathVariable int postId) {
		return ResponseEntity.ok(this.postService.getByIdPostResponse(postId));
	}

	@GetMapping(path = "/user/{userId}/posts")
	public ResponseEntity<List<PostDtoResponse>> getPostsByUser(@PathVariable int userId) {
		List<PostDtoResponse> newResponse = this.postService.getPostsByUser(userId);
		return new ResponseEntity<>(newResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/category/{categoryId}/posts")
	public ResponseEntity<List<PostDtoResponse>> getPostsByCategory(@PathVariable int categoryId) {
		List<PostDtoResponse> newResponse = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<>(newResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/posts/search/{title}")
	public ResponseEntity<List<PostDtoResponse>> searchPost(@PathVariable String title) {
		return ResponseEntity.ok(this.postService.searchPost(title));
	}

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDtoResponse> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable int postId) throws IOException {

		GetByIdPostResponse getByIdPostResponse = this.postService.getByIdPostResponse(postId);

		String fileName = this.fileService.uploadImage(path, image);
		UpdatePostRequest updatePostRequest = this.modelMapperService.forRequest().map(getByIdPostResponse,
				UpdatePostRequest.class);
		updatePostRequest.setImageName(fileName);

		PostDtoResponse updateResponse = this.postService.updatePost(updatePostRequest, postId);
		return new ResponseEntity<PostDtoResponse>(updateResponse, HttpStatus.OK);

	}
	// method to serve files

	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getresource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
