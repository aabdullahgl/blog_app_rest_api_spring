package com.egitim.blogapi.service;

import java.util.List;

import com.egitim.blogapi.service.dtos.category.CategoryDtoResponse;
import com.egitim.blogapi.service.dtos.category.CreateCategoryRequest;
import com.egitim.blogapi.service.dtos.category.GetByIdCategoryResponse;
import com.egitim.blogapi.service.dtos.category.UpdateCategoryRequest;

public interface CategoryService {

	public CategoryDtoResponse createCategory(CreateCategoryRequest categoryRequest);

	public CategoryDtoResponse updateCategory(UpdateCategoryRequest updateCategoryRequest,int categoryId);

	public void deleteCategory(int categoryId);

	public GetByIdCategoryResponse getByIdCategoryResponse(int categoryId);

	public List<CategoryDtoResponse> getAllCategoryResponse();
}
