package com.codershub.blogrepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codershub.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	
}
