package com.codershub.blog.services;

import java.util.List;

import com.codershub.blog.payloads.category.CategoryModel;


public interface CategoryService {
	//Create
	CategoryModel createCategory(CategoryModel categoryModel);
	
	//update
	CategoryModel updateCategory(CategoryModel categoryModel);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryModel getCategory(Integer categoryId);
	
	//getAll
	List<CategoryModel> getAllCategories();
}
