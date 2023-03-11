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
public class CreateCategoryRequest {


	@NotBlank
	@NotNull
	@Size(min = 4, message = "must be a minimum of 4 characters")
	private String categoryTitle;

	@NotBlank
	@NotNull
	@Size(min = 10, message = "must be a minimum of 10 characters")
	private String categoryDescription;
}
