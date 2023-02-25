package com.codershub.blog.controllers;

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

import com.codershub.blog.payloads.comment.ApiResponseCommentModel;
import com.codershub.blog.payloads.comment.ApiResponseCommentModels;
import com.codershub.blog.payloads.comment.CommentModel;
import com.codershub.blog.services.CommentService;
import com.codershub.blogutils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<ApiResponseCommentModel> addComment(@RequestBody CommentModel commentModel,
			@PathVariable Integer postId) {

		CommentModel createdComment = this.commentService.addComment(commentModel, postId);

		ApiResponseCommentModel apiResponseCommentModel = new ApiResponseCommentModel(true, HttpStatus.CREATED.value(),
				"Comment posted Successfully", createdComment);

		return new ResponseEntity<ApiResponseCommentModel>(apiResponseCommentModel, HttpStatus.CREATED);
	}

	@GetMapping("/post/{postId}/comment/{commentId}")
	public ResponseEntity<ApiResponseCommentModel> getCommentById(@PathVariable Integer commentId) {

		CommentModel commentModel = this.commentService.getCommentById(commentId);

		ApiResponseCommentModel apiResponseCommentModel = new ApiResponseCommentModel(true, HttpStatus.OK.value(),
				"Comment Fetched Successfully", commentModel);

		return new ResponseEntity<ApiResponseCommentModel>(apiResponseCommentModel, HttpStatus.OK);

	}

	// get by user
	@GetMapping("/post/{postId}/comments")
	public ResponseEntity<ApiResponseCommentModels> getCommentsByPost(@PathVariable Integer postId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_COMMENT_ID, required = false) String sortBy,
			@RequestParam(value = "sortMode", defaultValue = AppConstants.SORT_MODE, required = false) Integer sortMode) {

		ApiResponseCommentModels apiResponseCommentModels = this.commentService.getAllCommentsByPost(postId, pageNumber,
				pageSize, sortBy, sortMode);

		return new ResponseEntity<ApiResponseCommentModels>(apiResponseCommentModels, HttpStatus.OK);
	}

	@GetMapping("/comments")
	public ResponseEntity<ApiResponseCommentModels> getAllComments(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_COMMENT_ID, required = false) String sortBy,
			@RequestParam(value = "sortMode", defaultValue = AppConstants.SORT_MODE, required = false) Integer sortMode) {

		ApiResponseCommentModels apiResponseCommentModels = this.commentService.getAllComments(pageNumber, pageSize,
				sortBy, sortMode);

		return new ResponseEntity<ApiResponseCommentModels>(apiResponseCommentModels, HttpStatus.OK);

	}

	// PUT-update comment
	@PutMapping("post/comment/update")
	public ResponseEntity<ApiResponseCommentModel> updateComment(@Valid @RequestBody CommentModel commentModel) {
		CommentModel updatedComment = this.commentService.updateComment(commentModel);

		ApiResponseCommentModel apiResponseCommentModel = new ApiResponseCommentModel(true, HttpStatus.OK.value(),
				"Comment Updated Successfully", updatedComment);

		return new ResponseEntity<ApiResponseCommentModel>(apiResponseCommentModel, HttpStatus.OK);
	}
	
	@DeleteMapping("post/comment/delete/{commentId}")
	public ResponseEntity<ApiResponseCommentModel> deleteComment(@PathVariable Integer commentId) {

		this.commentService.deleteComment(commentId);

		ApiResponseCommentModel apiResponseCommentModel = new ApiResponseCommentModel(true, HttpStatus.OK.value(),
				"Comment Deleted Successfully", null);

		return new ResponseEntity<ApiResponseCommentModel>(apiResponseCommentModel, HttpStatus.OK);

	}

}
