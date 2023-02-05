package com.codershub.blog.services;

import java.util.List;

import com.codershub.blog.entities.Post;
import com.codershub.blog.payloads.post.PostModel;

public interface PostService {

	//Create
	PostModel createPost(PostModel postModel,Integer userId, Integer categoryId);
	
	//Update
	Post updatePost(PostModel postModel,Integer postId);
	
	//delete
	void deletePost(Integer id);
	
	//Get all posts
	List<Post> getAllPosts();
	
	//get Single post
	Post getPostById(Integer postId);
	
	//get all post by category
	List<Post> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<Post> getPostByUser(Integer userId);
}
