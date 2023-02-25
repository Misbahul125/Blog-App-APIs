package com.codershub.blog.services;

import com.codershub.blog.payloads.category.ApiResponseCategoryModels;
import com.codershub.blog.payloads.category.CategoryModel;

public interface CategoryService {
	// Create
	CategoryModel createCategory(CategoryModel categoryModel);

	// get
	CategoryModel getCategory(Integer categoryId);

	// getAll
	ApiResponseCategoryModels getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode);

	// update
	CategoryModel updateCategory(CategoryModel categoryModel);

	// delete
	void deleteCategory(Integer categoryId);

}
