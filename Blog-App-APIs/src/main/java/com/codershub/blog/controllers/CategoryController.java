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
import com.codershub.blog.payloads.category.CategoryModel;
import com.codershub.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryModel> createCategory(@Valid @RequestBody CategoryModel categoryModel)
	{
		CategoryModel createCategory=this.categoryService.createCategory(categoryModel);
		return new ResponseEntity<CategoryModel>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryModel> updateCategory(@Valid @RequestBody CategoryModel categoryModel,@PathVariable Integer catId)
	{
		CategoryModel updatedCategory=this.categoryService.updateCategory(categoryModel,catId);
		return new ResponseEntity<CategoryModel>(updatedCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponseCategoryModel> deleteCategory(@PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		
		ApiResponseCategoryModel apiResponseCategoryModel = new ApiResponseCategoryModel(
				true, 
				HttpStatus.OK.value(), 
				"User Deleted Successfully", 
				null
		);
		return new ResponseEntity<ApiResponseCategoryModel>(apiResponseCategoryModel,HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryModel> getCategory(@PathVariable Integer catId)
	{
		CategoryModel categoryModel=this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryModel>(categoryModel,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryModel>> getAllCategories()
	{
		List<CategoryModel> categories = this.categoryService.getAllCategories();
		
		return ResponseEntity.ok(categories);
	}
}
