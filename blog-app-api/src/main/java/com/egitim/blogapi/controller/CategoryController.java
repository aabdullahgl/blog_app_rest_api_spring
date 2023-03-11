package com.egitim.blogapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egitim.blogapi.service.CategoryService;
import com.egitim.blogapi.service.dtos.category.CategoryDtoResponse;
import com.egitim.blogapi.service.dtos.category.CreateCategoryRequest;
import com.egitim.blogapi.service.dtos.category.GetByIdCategoryResponse;
import com.egitim.blogapi.service.dtos.category.UpdateCategoryRequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDtoResponse> createCategory(
		@Valid	@RequestBody CreateCategoryRequest createCategoryRequest) {
		CategoryDtoResponse categoryDtoResponse = this.categoryService.createCategory(createCategoryRequest);
		return new ResponseEntity<CategoryDtoResponse>(categoryDtoResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{categoryId}")
	public ResponseEntity<CategoryDtoResponse> updateCategory(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest,
			@PathVariable int categoryId) {
		CategoryDtoResponse categoryDtoResponse = this.categoryService.updateCategory(updateCategoryRequest,
				categoryId);
		return new ResponseEntity<CategoryDtoResponse>(categoryDtoResponse, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("Kategori silindi");
	}

	@GetMapping(path = "/{categoryId}")
	public ResponseEntity<GetByIdCategoryResponse> getByIdCategoryResponse(@PathVariable int categoryId) {
		GetByIdCategoryResponse byIdCategoryResponse = this.categoryService.getByIdCategoryResponse(categoryId);
		return new ResponseEntity<GetByIdCategoryResponse>(byIdCategoryResponse, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CategoryDtoResponse>> getAllCategoryResponse() {
		List<CategoryDtoResponse> categoryDtoResponses = this.categoryService.getAllCategoryResponse();
		return new ResponseEntity<List<CategoryDtoResponse>>(categoryDtoResponses, HttpStatus.OK);
	}

}
