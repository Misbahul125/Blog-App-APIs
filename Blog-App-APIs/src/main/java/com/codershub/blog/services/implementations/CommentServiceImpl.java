package com.codershub.blog.services.implementations;

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

import com.codershub.blog.entities.Comment;
import com.codershub.blog.entities.Post;
import com.codershub.blog.exceptions.ResourceNotFoundException;
import com.codershub.blog.payloads.comment.ApiResponseCommentModels;
import com.codershub.blog.payloads.comment.CommentModel;
import com.codershub.blog.repositories.CommentRepo;
import com.codershub.blog.repositories.PostRepo;
import com.codershub.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentModel addComment(CommentModel commentModel, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "postId", postId));

		Comment comment = this.modelMapper.map(commentModel, Comment.class);

		comment.setPost(post);

		Comment savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentModel.class);

	}

	@Override
	public CommentModel getCommentById(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		return this.modelMapper.map(comment, CommentModel.class);

	}

	@Override
	public ApiResponseCommentModels getAllCommentsByPost(Integer postId, Integer pageNumber, Integer pageSize,
			String sortBy, Integer sortMode) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "postId", postId));

		// sorting format
		Sort sort = (sortMode == 0) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// paging format
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// retrieving paged data items
		Page<Comment> pageComments = this.commentRepo.findByPost(post, pageable);

		List<Comment> allComments = pageComments.getContent();

		List<CommentModel> commentModels = allComments.stream()
				.map((comment) -> this.modelMapper.map(comment, CommentModel.class)).collect(Collectors.toList());

		ApiResponseCommentModels apiResponseCommentModels = new ApiResponseCommentModels(true, HttpStatus.OK.value(),
				"Comment(s) Fetched Successfully", pageComments.getNumber(), pageComments.getSize(),
				pageComments.getTotalElements(), pageComments.getTotalPages(), pageComments.isLast(), commentModels);

		if (pageComments.getNumber() >= pageComments.getTotalPages()) {

			apiResponseCommentModels.setMessage("No more comment(s) found for this post");
			apiResponseCommentModels.setCommentModels(null);

		}

		return apiResponseCommentModels;

	}

	@Override
	public ApiResponseCommentModels getAllComments(Integer pageNumber, Integer pageSize, String sortBy,
			Integer sortMode) {

		// sorting format
		Sort sort = (sortMode == 0) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// paging format
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// retrieving paged data items
		Page<Comment> pageComments = this.commentRepo.findAll(pageable);

		List<Comment> allComments = pageComments.getContent();

		List<CommentModel> commentModels = allComments.stream()
				.map((comment) -> this.modelMapper.map(comment, CommentModel.class)).collect(Collectors.toList());

		ApiResponseCommentModels apiResponseCommentModels = new ApiResponseCommentModels(true, HttpStatus.OK.value(),
				"Comment(s) Fetched Successfully", pageComments.getNumber(), pageComments.getSize(),
				pageComments.getTotalElements(), pageComments.getTotalPages(), pageComments.isLast(), commentModels);

		if (pageComments.getNumber() >= pageComments.getTotalPages()) {

			apiResponseCommentModels.setMessage("No more comment(s) found for this post");
			apiResponseCommentModels.setCommentModels(null);

		}

		return apiResponseCommentModels;

	}

	@Override
	public CommentModel updateComment(CommentModel commentModel) {
		
		Comment comment = this.commentRepo.findById(commentModel.getCommentId())
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentModel.getCommentId()));
		
		if(commentModel.getContent() != null && !commentModel.getContent().isEmpty())
			comment.setContent(commentModel.getContent());
		
		Comment updatedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(updatedComment, CommentModel.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		this.commentRepo.delete(comment);

	}

}
