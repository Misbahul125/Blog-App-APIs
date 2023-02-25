package com.codershub.blog.services;

import com.codershub.blog.payloads.user.ApiResponseUserModels;
import com.codershub.blog.payloads.user.UserModel;

public interface UserService {
	
	public UserModel createUser(UserModel userModel);
	
	public UserModel getUserById(Integer userId);

	public ApiResponseUserModels getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, Integer sortMode);
	
	public UserModel updateUser(UserModel userModel);
	
	public void deleteUserById(Integer userId);
}
