package com.codershub.blog.payloads.post;

import java.util.Date;

import com.codershub.blog.entities.Category;
import com.codershub.blog.entities.User;

public class PostModel {
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private Category category;
	
	private User user;
}
