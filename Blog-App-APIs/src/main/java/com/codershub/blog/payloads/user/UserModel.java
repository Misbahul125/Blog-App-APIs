package com.codershub.blog.payloads.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
	
	private int id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String role;
	
	private boolean active;
	
	private String imageURL;
	
	private String about;

}
