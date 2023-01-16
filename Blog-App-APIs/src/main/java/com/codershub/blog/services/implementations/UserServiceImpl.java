package com.codershub.blog.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codershub.blog.entities.User;
import com.codershub.blog.exceptions.ResourceNotFoundException;
import com.codershub.blog.payloads.user.UserModel;
import com.codershub.blog.services.UserService;
import com.codershub.blog.repositories.UserRepository;

@Service
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
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(
						(() -> new ResourceNotFoundException("User", "user ID", Integer.toString(userId)))
						);
		
		return this.userEntityToUserModel(user);
	}

	@Override
	public List<UserModel> getAllUsers() {
		
		List<User> users = this.userRepository.findAll();
		
		List<UserModel> userModels = users.stream().map(userModel -> this.userEntityToUserModel(userModel)).collect(Collectors.toList());
		
		return userModels;
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		
		//getting the user from DB, also helps us to check if user is present in DB or not
		User userFromDB = this.userRepository.findById(userModel.getId())
				.orElseThrow(
						(() -> new ResourceNotFoundException("User", "user ID", Integer.toString(userModel.getId())))
						);
		
		userFromDB = this.userModelToUserEntity(userModel);
		
		User updatedUser = this.userRepository.save(userFromDB);
		
		return this.userEntityToUserModel(updatedUser);
	}

	@Override
	public void deleteUserById(Integer userId) {
		
		User userFromDB = this.userRepository.findById(userId)
		.orElseThrow(
				(() -> new ResourceNotFoundException("User", "user ID", Integer.toString(userId)))
				);
		
		this.userRepository.delete(userFromDB);
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
