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

import com.codershub.blog.payloads.post.PostModel;
import com.codershub.blog.services.PostService;

@RestController
@RequestMapping("api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//create 
	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostModel> createPost(@RequestBody PostModel postModel,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostModel createPost=this.postService.createPost(postModel, userId, categoryId);
		return new ResponseEntity<PostModel>(createPost,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostModel>> getPostsbyUser(@PathVariable Integer userId)
	{
		List<PostModel> posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostModel>>(posts,HttpStatus.OK);
	}

	//get by category
		@GetMapping("/categories/{categoryId}/posts")
		public ResponseEntity<List<PostModel>> getPostsbyCategory(@PathVariable Integer categoryId)
		{
			List<PostModel> posts=this.postService.getPostByCategory(categoryId);
			return new ResponseEntity<List<PostModel>>(posts,HttpStatus.OK);
		}

}
