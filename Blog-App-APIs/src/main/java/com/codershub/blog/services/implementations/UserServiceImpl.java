package com.codershub.blog.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codershub.blog.entities.User;
import com.codershub.blog.payloads.user.UserModel;
import com.codershub.blog.services.UserService;
import com.codershub.blogrepositories.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel createUser(UserModel userModel) {
		
		User user = this.userModelToUserEntity(userModel);
		
		user = this.userRepository.save(user);
		
		return this.userEntityToUserModel(user);
	}

	@Override
	public UserModel getUserById(Integer userId) {
		
		return null;
	}

	@Override
	public List<UserModel> getAllUsers() {
		
		return null;
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		
		return null;
	}

	@Override
	public void deleteUserById(Integer userId) {
		
		
	}
	
	private UserModel userEntityToUserModel(User user) {
		
		UserModel userModel = new UserModel(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getPassword(),
				user.getRole(),
				user.isActive(),
				user.getImageURL(),
				user.getAbout()
				);
		
		return userModel;
	}
	
	private User userModelToUserEntity(UserModel userModel) {
		
		User user = new User(
				userModel.getId(),
				userModel.getName(),
				userModel.getEmail(),
				userModel.getPassword(),
				userModel.getRole(),
				userModel.isActive(),
				userModel.getImageURL(),
				userModel.getAbout()
				);
		
		return user;
	}

}
