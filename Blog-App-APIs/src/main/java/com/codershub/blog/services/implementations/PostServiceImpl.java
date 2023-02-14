package com.codershub.blog.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codershub.blog.entities.Category;
import com.codershub.blog.entities.Post;
import com.codershub.blog.entities.User;
import com.codershub.blog.exceptions.ResourceNotFoundException;
import com.codershub.blog.payloads.post.PostModel;
import com.codershub.blog.repositories.CategoryRepository;
import com.codershub.blog.repositories.PostRepo;
import com.codershub.blog.repositories.UserRepository;
import com.codershub.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public PostModel createPost(PostModel postModel,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		Post post=this.modelMapper.map(postModel, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostModel.class);
	}

	@Override
	public Post updatePost(PostModel postModel, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostModel> getPostByCategory(Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		
		List<PostModel> postModels=posts.stream().map((post)->this.modelMapper.map(post, PostModel.class)).collect(Collectors.toList());
		
		
		return postModels;
	}

	@Override
	public List<PostModel> getPostByUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		
		List<PostModel> postModels=posts.stream().map((post)->this.modelMapper.map(post, PostModel.class)).collect(Collectors.toList());
		
		return postModels;
	}
	
	@Override
	public List<Post> searchPosts(String keyword)
	{
		return null;
	}

}
