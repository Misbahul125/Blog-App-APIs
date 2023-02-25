package com.codershub.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codershub.blog.entities.Comment;
import com.codershub.blog.entities.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	Page<Comment> findByPost(Post post, Pageable pageable);

}
