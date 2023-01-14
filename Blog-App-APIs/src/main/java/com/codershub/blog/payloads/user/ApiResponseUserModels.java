package com.codershub.blog.payloads.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponseUserModels {
	
	private boolean success;
	
	private int code;
	
	private String message;
	
	private List<UserModel> userModels;

}
