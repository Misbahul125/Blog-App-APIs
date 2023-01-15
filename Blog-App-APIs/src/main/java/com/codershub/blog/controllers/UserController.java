package com.codershub.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codershub.blog.payloads.user.ApiResponseUserModel;
import com.codershub.blog.payloads.user.UserModel;
import com.codershub.blog.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<ApiResponseUserModel> createUser(@RequestBody UserModel userModel) {
		
		UserModel createdUser = this.userService.createUser(userModel);
		
		ApiResponseUserModel apiResponseUserModel = new ApiResponseUserModel(
				true, 
				HttpStatus.CREATED.value(), 
				"User Created Successfully", 
				createdUser
		);
		
		return new ResponseEntity<ApiResponseUserModel>(apiResponseUserModel, HttpStatus.CREATED);
	}

}
