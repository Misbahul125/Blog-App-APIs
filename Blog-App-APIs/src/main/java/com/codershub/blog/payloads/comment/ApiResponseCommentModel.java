package com.codershub.blog.payloads.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseCommentModel {

	private boolean success;// Denotes whether api call is successful

	private int code;// shows Http codes

	private String message;// custom message

	private CommentModel commentModel;

}
