package com.codershub.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codershub.blog.payloads.category.ApiResponseCategoryModel;
import com.codershub.blog.payloads.post.ApiResponsePostModel;
import com.codershub.blog.payloads.post.ApiResponsePostModels;
import com.codershub.blog.payloads.post.PostModel;
import com.codershub.blog.services.PostService;

@RestController
@RequestMapping("api/")
public class PostController {

	@Autowired
	private PostService postService;

	// create
	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<ApiResponsePostModel> createPost(@RequestBody PostModel postModel, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostModel createPost = this.postService.createPost(postModel, userId, categoryId);
		
		ApiResponsePostModel apiResponsePostModel = new ApiResponsePostModel(
				true, 
				HttpStatus.CREATED.value(), 
				"Post Created Successfully", 
				createPost
		);
		
		return new ResponseEntity<ApiResponsePostModel>(apiResponsePostModel, HttpStatus.CREATED);
	}

	// get by user
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<ApiResponsePostModels> getPostsByUser(@PathVariable Integer userId) {
		List<PostModel> posts = this.postService.getPostByUser(userId);
		
		
		ApiResponsePostModels apiResponsePostModels = new ApiResponsePostModels(
				true, 
				HttpStatus.OK.value(), 
				"Post(s) Fetched Successfully", 
				posts
		);
		
		return new ResponseEntity<ApiResponsePostModels>(apiResponsePostModels, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<ApiResponsePostModels> getPostsbyCategory(@PathVariable Integer categoryId) {
		List<PostModel> posts = this.postService.getPostByCategory(categoryId);
		
		
		ApiResponsePostModels apiResponsePostModels = new ApiResponsePostModels(
				true, 
				HttpStatus.OK.value(), 
				"Post(s) Fetched Successfully", 
				posts
		);
		
		return new ResponseEntity<ApiResponsePostModels>(apiResponsePostModels, HttpStatus.OK);
	}

}
