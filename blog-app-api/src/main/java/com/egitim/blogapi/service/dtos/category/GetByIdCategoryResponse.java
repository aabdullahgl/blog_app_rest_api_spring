package com.egitim.blogapi.service.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCategoryResponse {
	private int categoryId;

	private String categoryTitle;

	private String categoryDescription;
}
