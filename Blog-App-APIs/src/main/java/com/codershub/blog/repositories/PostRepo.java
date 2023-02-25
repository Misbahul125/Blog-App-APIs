package com.codershub.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codershub.blog.entities.Category;
import com.codershub.blog.entities.Post;
import com.codershub.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	Page<Post> findByUser(User user, Pageable pageable);
	Page<Post> findByCategory(Category category, Pageable pageable);
	Page<Post> findByContentContaining(String searchKey, Pageable pageable);
	
	/*
	 * @Query("select p from posts where p.title like :searchKey") Page<Post>
	 * searchByTitle(@Param("searchKey") String title, Pageable pageable);
	 */
}
