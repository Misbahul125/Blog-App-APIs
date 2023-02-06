package com.codershub.blog.payloads.post;

import java.util.Date;

import com.codershub.blog.payloads.category.CategoryModel;
import com.codershub.blog.payloads.user.UserModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostModel {
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryModel category;
	
	private UserModel user;
}
