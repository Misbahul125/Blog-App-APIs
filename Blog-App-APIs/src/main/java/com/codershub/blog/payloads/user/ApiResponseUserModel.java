package com.codershub.blog.payloads.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponseUserModel {
	
	private boolean success;
	
	private int code;
	
	private String message;

	private UserModel userModel;
	
}
