package com.codershub.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codershub.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}