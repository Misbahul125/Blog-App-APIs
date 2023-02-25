package com.codershub.blog.services;

import java.util.List;

import com.codershub.blog.payloads.post.ApiResponsePostModels;
import com.codershub.blog.payloads.post.PostModel;

public interface PostService {

	// Create
	PostModel createPost(PostModel postModel, Integer userId, Integer categoryId);

	// get Single post
	PostModel getPostById(Integer postId);

	  // get all post by category 
	//ApiResponsePostModels getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode);

	// get all posts by user 
	ApiResponsePostModels getPostByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode);

	// Get all posts
	ApiResponsePostModels getAllPosts(Integer pageNumber,Integer pageSize, String sortBy, Integer sortMode);

	// search posts
	ApiResponsePostModels searchPosts(String keyword);

	// Update
	PostModel updatePost(PostModel postModel);

	// delete
	void deletePost(Integer id);

}
