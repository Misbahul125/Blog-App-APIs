package com.codershub.blog.services;

import java.util.List;

import com.codershub.blog.payloads.user.UserModel;

public interface UserService {
	
	public UserModel createUser(UserModel userModel);
	
	public UserModel getUserById(Integer userId);

	public List<UserModel> getAllUsers();
	
	public UserModel updateUser(UserModel userModel);
	
	public void deleteUserById(Integer userId);
}
