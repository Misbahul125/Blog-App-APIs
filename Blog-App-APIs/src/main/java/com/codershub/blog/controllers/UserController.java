package com.codershub.blog.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.codershub.blog.payloads.user.ApiResponseUserModel;
import com.codershub.blog.payloads.user.ApiResponseUserModels;
import com.codershub.blog.payloads.user.UserModel;
import com.codershub.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
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
	@PutMapping("/")
	public ResponseEntity<ApiResponseUserModel> updateUser(@RequestBody UserModel userModel)
	{
		UserModel updatedUser=this.userService.updateUser(userModel);
		
		ApiResponseUserModel apiResponseUserModel = new ApiResponseUserModel(
				true, 
				HttpStatus.OK.value(), 
				"User Updated Successfully", 
				updatedUser
		);
		
		return new ResponseEntity<ApiResponseUserModel>(apiResponseUserModel, HttpStatus.OK);
	}
	
	//DELETE-delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponseUserModel> deleteUser(@PathVariable Integer userId)
	{
		this.userService.deleteUserById(userId);
		
		ApiResponseUserModel apiResponseUserModel = new ApiResponseUserModel(
				true, 
				HttpStatus.OK.value(), 
				"User Deleted Successfully", 
				null
		);
		
		return new ResponseEntity<ApiResponseUserModel>(apiResponseUserModel, HttpStatus.OK);
	}
	
	//GET-get user
	//Multiple user
	@GetMapping("/")
	public ResponseEntity<ApiResponseUserModels> getAllUsers()
	{
		List<UserModel> userModels = this.userService.getAllUsers();
		
		ApiResponseUserModels apiResponseUserModels = new ApiResponseUserModels(
				true, 
				HttpStatus.OK.value(), 
				"Users Fetched Successfully", 
				userModels
		);
		
		return new ResponseEntity<ApiResponseUserModels>(apiResponseUserModels, HttpStatus.OK);
	}

	//Single user
	@GetMapping("/{userId}")
	public ResponseEntity<ApiResponseUserModel> getSingleUser(@PathVariable Integer userId)
	{
		UserModel userModel = this.userService.getUserById(userId);
		
		ApiResponseUserModel apiResponseUserModel = new ApiResponseUserModel(
				true, 
				HttpStatus.OK.value(), 
				"User Fetched Successfully", 
				userModel
		);
		
		return new ResponseEntity<ApiResponseUserModel>(apiResponseUserModel, HttpStatus.OK);
	}
}
