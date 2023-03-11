package com.egitim.blogapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.egitim.blogapi.repository.CategoryRepository;
import com.egitim.blogapi.repository.PostRepository;
import com.egitim.blogapi.repository.UserRepository;
import com.egitim.blogapi.service.CategoryService;
import com.egitim.blogapi.service.PostService;
import com.egitim.blogapi.service.UserService;

@SpringBootTest
class BlogAppApiApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostService postService;
	@Autowired
	private PostRepository postRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void userServiceTest() {
		String userServiceName = userService.getClass().getName();
		String userServicePackageName = userService.getClass().getPackageName();
		System.out.println("user service name = " + userServiceName);
		System.out.println("user service package name = " + userServicePackageName);
	}

	@Test
	void userRepositoryTest() {
		String userRepositoryName = userRepository.getClass().getName();
		String userRepositoryPackageName = userRepository.getClass().getPackageName();
		System.out.println("user repository name = " + userRepositoryName);
		System.out.println("user repository package name = " + userRepositoryPackageName);
	}

	@Test
	void categoryService() {
		System.out.println("-----------------------------------------------------");
		String categoryServiceName = categoryService.getClass().getName();
		String categoryServicePackageName = categoryService.getClass().getPackageName();
		System.out.println("category service name = " + categoryServiceName);
		System.out.println("category service package name = " + categoryServicePackageName);
	}

	@Test
	void categoryRepository() {
		String categoryRepositoryName = categoryRepository.getClass().getName();
		String categoryRepositoryPackageName = categoryRepository.getClass().getPackageName();
		System.out.println("category repository name = " + categoryRepositoryName);
		System.out.println("category repository package name = " + categoryRepositoryPackageName);
	}
	@Test
	void postService() {
		System.out.println("-----------------------------------------------------");
		String postServiceName = postService.getClass().getName();
		String postServicePackageName = postService.getClass().getPackageName();
		System.out.println("post service name = " + postServiceName);
		System.out.println("post service package name = " + postServicePackageName);
	}

	@Test
	void postRepository() {
		String postRepositoryName = postRepository.getClass().getName();
		String postRepositoryPackageName = postRepository.getClass().getPackageName();
		System.out.println("post repository name = " + postRepositoryName);
		System.out.println("post repository package name = " + postRepositoryPackageName);
	}

}
