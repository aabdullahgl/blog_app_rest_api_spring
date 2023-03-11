package com.egitim.blogapi.service.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {

	@NotBlank
	@NotNull
	@Size(min = 4, message = "Minimum 4 karakter olmalı")
	private String categoryTitle;

	@NotBlank
	@NotNull
	@Size(min = 10, message = "Minimum 10 karekter olmalı")
	private String categoryDescription;
}
