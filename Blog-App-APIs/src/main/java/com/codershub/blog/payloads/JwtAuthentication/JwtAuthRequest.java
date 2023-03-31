package com.codershub.blog.payloads.JwtAuthentication;

import lombok.Data;

@Data
public class JwtAuthRequest {
	//email is considered as user name
	private String userName;
	
	private String password;
}
