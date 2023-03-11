package com.egitim.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.egitim.blogapi.core.exception.ResourceNotFoundException;
import com.egitim.blogapi.core.mapper.ModelMapperService;
import com.egitim.blogapi.model.Category;
import com.egitim.blogapi.repository.CategoryRepository;
import com.egitim.blogapi.service.CategoryService;
import com.egitim.blogapi.service.dtos.category.CategoryDtoResponse;
import com.egitim.blogapi.service.dtos.category.CreateCategoryRequest;
import com.egitim.blogapi.service.dtos.category.GetByIdCategoryResponse;
import com.egitim.blogapi.service.dtos.category.UpdateCategoryRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private  final CategoryRepository categoryRepository;
	private final ModelMapperService modelMapperService;

	@Override
	public CategoryDtoResponse createCategory(CreateCategoryRequest categoryRequest) {

		Category category = this.modelMapperService.forRequest().map(categoryRequest, Category.class);
		Category response = this.categoryRepository.save(category);
		return this.modelMapperService.forResponse().map(response, CategoryDtoResponse.class);
	}

	@Override
	public CategoryDtoResponse updateCategory(UpdateCategoryRequest updateCategoryRequest, int categoryId) {
		Category categoryByIdExist = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", categoryId));
		this.modelMapperService.forRequest().map(updateCategoryRequest, categoryByIdExist);
		Category response = this.categoryRepository.save(categoryByIdExist);
		return this.modelMapperService.forResponse().map(response, CategoryDtoResponse.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category categoryByIdExist = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", categoryId));
		this.categoryRepository.delete(categoryByIdExist);

	}

	@Override
	public GetByIdCategoryResponse getByIdCategoryResponse(int categoryId) {
		Category categoryByIdExist = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", " Category Id", categoryId));
		return this.modelMapperService.forResponse().map(categoryByIdExist, GetByIdCategoryResponse.class);
	}

	@Override
	public List<CategoryDtoResponse> getAllCategoryResponse() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDtoResponse> categoryDtoResponses = categories.stream()
				.map(category -> this.modelMapperService.forResponse().map(category, CategoryDtoResponse.class))
				.collect(Collectors.toList());
		return categoryDtoResponses;

	}
}
