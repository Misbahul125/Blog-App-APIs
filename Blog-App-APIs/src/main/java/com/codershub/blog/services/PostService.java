package com.codershub.blog.services;

import java.util.List;

import com.codershub.blog.entities.Post;
import com.codershub.blog.payloads.post.PostModel;

public interface PostService {

	// Create
	PostModel createPost(PostModel postModel, Integer userId, Integer categoryId);

	// get Single post
	PostModel getPostById(Integer postId);

	// get all post by category
	List<PostModel> getPostByCategory(Integer categoryId);

	// get all posts by user
	List<PostModel> getPostByUser(Integer userId);

	// Get all posts
	List<PostModel> getAllPosts(Integer pageNumber,Integer pageSize);

	// search posts
	List<PostModel> searchPosts(String keyword);

	// Update
	PostModel updatePost(PostModel postModel);

	// delete
	void deletePost(Integer id);

}
