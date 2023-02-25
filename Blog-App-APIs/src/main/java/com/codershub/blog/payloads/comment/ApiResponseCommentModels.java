package com.codershub.blog.payloads.comment;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseCommentModels {

	private boolean success;// Denotes whether api call is successful

	private int code;// shows Http codes

	private String message;// custom message

	private Integer pageNumber;

	private Integer pageSize;

	private Long totalItems;

	private Integer totalPages;

	private Boolean isLastPage;

	private List<CommentModel> commentModels;

}
