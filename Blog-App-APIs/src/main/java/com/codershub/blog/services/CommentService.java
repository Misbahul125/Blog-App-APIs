package com.codershub.blog.services;

import com.codershub.blog.payloads.comment.ApiResponseCommentModels;
import com.codershub.blog.payloads.comment.CommentModel;

public interface CommentService {
	
	CommentModel addComment(CommentModel commentModel, Integer postId);
	
	CommentModel getCommentById(Integer commentId);
	
	ApiResponseCommentModels getAllCommentsByPost(Integer postId, Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode);
	
	ApiResponseCommentModels getAllComments(Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode);
	
	CommentModel updateComment(CommentModel commentModel);
	
	void deleteComment(Integer commentId);

}
