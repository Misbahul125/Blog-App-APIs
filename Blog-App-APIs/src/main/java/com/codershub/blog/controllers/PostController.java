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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codershub.blog.payloads.post.ApiResponsePostModel;
import com.codershub.blog.payloads.post.ApiResponsePostModels;
import com.codershub.blog.payloads.post.PostModel;
import com.codershub.blog.services.PostService;
import com.codershub.blogutils.Constants;

@RestController
@RequestMapping("api")
public class PostController {

	@Autowired
	private PostService postService;

	// create
	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<ApiResponsePostModel> createPost(@RequestBody PostModel postModel,
			@PathVariable Integer userId, @PathVariable Integer categoryId) {
		PostModel createPost = this.postService.createPost(postModel, userId, categoryId);

		ApiResponsePostModel apiResponsePostModel = new ApiResponsePostModel(true, HttpStatus.CREATED.value(),
				"Post Created Successfully", createPost);

		return new ResponseEntity<ApiResponsePostModel>(apiResponsePostModel, HttpStatus.CREATED);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<ApiResponsePostModel> getPostById(@PathVariable Integer postId) {

		PostModel postModel = this.postService.getPostById(postId);

		ApiResponsePostModel apiResponsePostModel = new ApiResponsePostModel(true, HttpStatus.OK.value(),
				"Post Fetched Successfully", postModel);

		return new ResponseEntity<ApiResponsePostModel>(apiResponsePostModel, HttpStatus.OK);

	}

//	// get by category
//	@GetMapping("/categories/{categoryId}/posts")
//	public ResponseEntity<ApiResponsePostModels> getPostsbyCategory(@PathVariable Integer categoryId,
//			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
//			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
//			@RequestParam(value = "sortMode", defaultValue = "0", required = false) Integer sortMode) {
//
//		ApiResponsePostModels apiResponsePostModels = this.postService.getPostByUser(userId, pageNumber, pageSize,
//				sortBy, sortMode);
//
//		return new ResponseEntity<ApiResponsePostModels>(apiResponsePostModels, HttpStatus.OK);
//		
//	}

	// get by user
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<ApiResponsePostModels> getPostsByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortMode", defaultValue = "0", required = false) Integer sortMode) {

		ApiResponsePostModels apiResponsePostModels = this.postService.getPostByUser(userId, pageNumber, pageSize,
				sortBy, sortMode);

		return new ResponseEntity<ApiResponsePostModels>(apiResponsePostModels, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<ApiResponsePostModels> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortMode", defaultValue = "0", required = false) Integer sortMode) {

		ApiResponsePostModels apiResponsePostModels = this.postService.getAllPosts(pageNumber, pageSize, sortBy,
				sortMode);

		return new ResponseEntity<ApiResponsePostModels>(apiResponsePostModels, HttpStatus.OK);

	}

	@PutMapping("/post")
	public ResponseEntity<ApiResponsePostModel> updatePost(@RequestBody PostModel postModel) {

		PostModel updatedPost = this.postService.updatePost(postModel);

		ApiResponsePostModel apiResponsePostModel = new ApiResponsePostModel(true, HttpStatus.OK.value(),
				"Post Updated Successfully", updatedPost);

		return new ResponseEntity<ApiResponsePostModel>(apiResponsePostModel, HttpStatus.OK);

	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponsePostModel> deletePost(@PathVariable Integer postId) {

		this.postService.deletePost(postId);

		ApiResponsePostModel apiResponsePostModel = new ApiResponsePostModel(true, HttpStatus.OK.value(),
				"Post Deleted Successfully", null);

		return new ResponseEntity<ApiResponsePostModel>(apiResponsePostModel, HttpStatus.OK);

	}

}
