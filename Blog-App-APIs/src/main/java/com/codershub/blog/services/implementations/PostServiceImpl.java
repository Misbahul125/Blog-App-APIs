package com.codershub.blog.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codershub.blog.entities.Category;
import com.codershub.blog.entities.Post;
import com.codershub.blog.entities.User;
import com.codershub.blog.exceptions.ResourceNotFoundException;
import com.codershub.blog.payloads.post.ApiResponsePostModels;
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
	public PostModel createPost(PostModel postModel, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		Post post = this.modelMapper.map(postModel, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostModel.class);
	}

	@Override
	public PostModel getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow((() -> new ResourceNotFoundException("Post", "post ID", postId)));

		return this.modelMapper.map(post, PostModel.class);
	}

	@Override
	public ApiResponsePostModels getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize,
			String sortBy, Integer sortMode) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", "CategoryId", categoryId));

		// sorting format
		Sort sort = (sortMode == 0) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// paging format
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// retrieving paged data items
		Page<Post> pagePosts = this.postRepo.findByCategory(category, pageable);

		List<Post> allPosts = pagePosts.getContent();

		List<PostModel> postModels = allPosts.stream().map((post) -> this.modelMapper.map(post, PostModel.class))
				.collect(Collectors.toList());

		ApiResponsePostModels apiResponsePostModels = new ApiResponsePostModels(true, HttpStatus.OK.value(),
				"Posts Fetched Successfully", pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalElements(),
				pagePosts.getTotalPages(), pagePosts.isLast(), postModels);

		if (pagePosts.getNumber() >= pagePosts.getTotalPages()) {

			apiResponsePostModels.setMessage("No more post(s) found");
			apiResponsePostModels.setPostModels(null);

		}

		return apiResponsePostModels;
	}

	@Override
	public ApiResponsePostModels getPostByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy,
			Integer sortMode) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", "UserId", userId));

		// sorting format
		Sort sort = (sortMode == 0) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// paging format
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// retrieving paged data items
		Page<Post> pagePosts = this.postRepo.findByUser(user, pageable);

		List<Post> allPosts = pagePosts.getContent();

		List<PostModel> postModels = allPosts.stream().map((post) -> this.modelMapper.map(post, PostModel.class))
				.collect(Collectors.toList());

		ApiResponsePostModels apiResponsePostModels = new ApiResponsePostModels(true, HttpStatus.OK.value(),
				"Posts Fetched Successfully", pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalElements(),
				pagePosts.getTotalPages(), pagePosts.isLast(), postModels);

		if (pagePosts.getNumber() >= pagePosts.getTotalPages()) {

			apiResponsePostModels.setMessage("No more post(s) found for this user");
			apiResponsePostModels.setPostModels(null);

		}

		return apiResponsePostModels;
	}

	@Override
	public ApiResponsePostModels getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode) {

		// sorting format
		Sort sort = (sortMode == 0) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// paging format
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// retrieving paged data items
		Page<Post> pagePosts = this.postRepo.findAll(pageable);

		List<Post> allPosts = pagePosts.getContent();

		List<PostModel> postModels = allPosts.stream().map((post) -> this.modelMapper.map(post, PostModel.class))
				.collect(Collectors.toList());

		ApiResponsePostModels apiResponsePostModels = new ApiResponsePostModels(true, HttpStatus.OK.value(),
				"Posts Fetched Successfully", pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalElements(),
				pagePosts.getTotalPages(), pagePosts.isLast(), postModels);

		if (pagePosts.getNumber() >= pagePosts.getTotalPages()) {

			apiResponsePostModels.setMessage("No more post(s) found");
			apiResponsePostModels.setPostModels(null);

		}

		return apiResponsePostModels;
	}

	@Override
	public ApiResponsePostModels searchPostsByContent(String searchKey, Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode) {

		// sorting format
		Sort sort = (sortMode == 0) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// paging format
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// retrieving paged data items
		Page<Post> pagePosts = this.postRepo.findByContentContaining(searchKey, pageable);

		List<Post> allPosts = pagePosts.getContent();

		List<PostModel> postModels = allPosts.stream().map((post) -> this.modelMapper.map(post, PostModel.class))
				.collect(Collectors.toList());

		ApiResponsePostModels apiResponsePostModels = new ApiResponsePostModels(true, HttpStatus.OK.value(),
				"Posts Fetched Successfully", pagePosts.getNumber(), pagePosts.getSize(), pagePosts.getTotalElements(),
				pagePosts.getTotalPages(), pagePosts.isLast(), postModels);

		if (pagePosts.getNumber() >= pagePosts.getTotalPages()) {

			apiResponsePostModels.setMessage("No more post(s) found");
			apiResponsePostModels.setPostModels(null);

		}

		return apiResponsePostModels;

	}

	@Override
	public PostModel updatePost(PostModel postModel) {

		Post post = this.postRepo.findById(postModel.getPostId())
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post ID", postModel.getPostId()));

		if (postModel.getTitle() != null && !postModel.getTitle().isEmpty())
			post.setTitle(postModel.getTitle());

		if (postModel.getContent() != null && !postModel.getContent().isEmpty())
			post.setContent(postModel.getContent());

		if (postModel.getImageName() != null && !postModel.getImageName().isEmpty())
			post.setImageName(postModel.getImageName());

		if (postModel.getCategory() != null && postModel.getCategory().getCategoryId() != null
				&& postModel.getCategory().getCategoryId() != post.getCategory().getCategoryId()) {
			Category category = this.categoryRepo.findById(postModel.getCategory().getCategoryId()).orElseThrow(
					() -> new ResourceNotFoundException("Post", "CategoryId", postModel.getCategory().getCategoryId()));
			post.setCategory(category);
		}

		if (postModel.getUser() != null && postModel.getUser().getId() != null
				&& postModel.getUser().getId() != post.getUser().getId()) {
			User user = this.userRepo.findById(postModel.getUser().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Post", "UserId", postModel.getUser().getId()));
			post.setUser(user);
		}

		Post updatedPost = this.postRepo.save(post);

		return this.modelMapper.map(updatedPost, PostModel.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post ID", postId));

		this.postRepo.delete(post);

	}

}
