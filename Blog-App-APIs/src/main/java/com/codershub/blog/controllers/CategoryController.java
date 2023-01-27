package com.codershub.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.codershub.blog.payloads.category.ApiResponseCategoryModel;
import com.codershub.blog.payloads.category.ApiResponseCategoryModels;
import com.codershub.blog.payloads.category.CategoryModel;
import com.codershub.blog.payloads.user.ApiResponseUserModel;
import com.codershub.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<ApiResponseCategoryModel> createCategory(@Valid @RequestBody CategoryModel categoryModel)
	{
		
		CategoryModel createdCategory = this.categoryService.createCategory(categoryModel);
		
		ApiResponseCategoryModel apiResponseCategoryModel = new ApiResponseCategoryModel(
				true, 
				HttpStatus.CREATED.value(), 
				"Category Created Successfully", 
				createdCategory
		);
		
		return new ResponseEntity<ApiResponseCategoryModel>(apiResponseCategoryModel, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/")
	public ResponseEntity<ApiResponseCategoryModel> updateCategory(@Valid @RequestBody CategoryModel categoryModel)
	{
		CategoryModel updatedCategory = this.categoryService.updateCategory(categoryModel);
		
		ApiResponseCategoryModel apiResponseCategoryModel = new ApiResponseCategoryModel(
				true, 
				HttpStatus.OK.value(), 
				"Category Updated Successfully", 
				updatedCategory
		);
		
		return new ResponseEntity<ApiResponseCategoryModel>(apiResponseCategoryModel,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponseCategoryModel> deleteCategory(@PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		
		ApiResponseCategoryModel apiResponseCategoryModel = new ApiResponseCategoryModel(
				true, 
				HttpStatus.OK.value(), 
				"Category Deleted Successfully", 
				null
		);
		return new ResponseEntity<ApiResponseCategoryModel>(apiResponseCategoryModel,HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<ApiResponseCategoryModel> getCategory(@PathVariable Integer catId)
	{
		CategoryModel categoryModel=this.categoryService.getCategory(catId);
		
		ApiResponseCategoryModel apiResponseCategoryModel = new ApiResponseCategoryModel(
				true, 
				HttpStatus.CREATED.value(), 
				"Category Fetched Successfully", 
				categoryModel
		);
		
		return new ResponseEntity<ApiResponseCategoryModel>(apiResponseCategoryModel,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<ApiResponseCategoryModels> getAllCategories()
	{
		List<CategoryModel> categories = this.categoryService.getAllCategories();
		
		ApiResponseCategoryModels apiResponseCategoryModels = new ApiResponseCategoryModels(
				true, 
				HttpStatus.OK.value(), 
				"Categories Fetched Successfully", 
				categories
		);
		
		return new ResponseEntity<ApiResponseCategoryModels>(apiResponseCategoryModels,HttpStatus.OK);
	}
}
