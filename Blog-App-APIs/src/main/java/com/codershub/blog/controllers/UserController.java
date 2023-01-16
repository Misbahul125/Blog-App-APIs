package com.codershub.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//POST-create user
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
	
	//PUT-update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel, Integer userId)
	{
		UserModel updatedUser=this.userService.updateUser(userModel);
		return ResponseEntity.ok(updatedUser);
	}
	
	//DELETE-delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponseUserModel> deleteUser(Integer userId)
	{
		this.deleteUser(userId);
		return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK); 
	}
	
	//GET-get user
	//Multiple user
	@GetMapping("/")
	public ResponseEntity<List<UserModel>> getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	//Single user
	@GetMapping("/{userId}")
	public ResponseEntity getSingleUser(Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
