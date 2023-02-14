package com.codershub.blog.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserModel createUser(UserModel userModel) {
		
		User user = this.modelMapper.map(userModel, User.class);
		
		user = this.userRepository.save(user);
		
		return this.modelMapper.map(user, UserModel.class);
	}

	@Override
	public UserModel getUserById(Integer userId) {
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(
						(() -> new ResourceNotFoundException("User", "user ID", userId))
						);
		
		return this.modelMapper.map(user, UserModel.class);
	}

	@Override
	public List<UserModel> getAllUsers() {
		
		List<User> users = this.userRepository.findAll();
		
		List<UserModel> userModels = users.stream().map(user -> this.modelMapper.map(user, UserModel.class)).collect(Collectors.toList());
		
		return userModels;
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		
		//getting the user from DB, also helps us to check if user is present in DB or not
		User userFromDB = this.userRepository.findById(userModel.getId())
				.orElseThrow(
						(() -> new ResourceNotFoundException("User", "user ID", userModel.getId()))
						);
		
		userFromDB = this.modelMapper.map(userModel, User.class);
		
		User updatedUser = this.userRepository.save(userFromDB);
		
		return this.modelMapper.map(updatedUser, UserModel.class);
	}

	@Override
	public void deleteUserById(Integer userId) {
		
		User userFromDB = this.userRepository.findById(userId)
		.orElseThrow(
				(() -> new ResourceNotFoundException("User", "user ID", userId))
				);
		
		this.userRepository.delete(userFromDB);
	}

}
