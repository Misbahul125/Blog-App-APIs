package com.codershub.blog.payloads.post;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.codershub.blog.payloads.category.CategoryModel;
import com.codershub.blog.payloads.comment.CommentModel;
import com.codershub.blog.payloads.user.UserModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {
	
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryModel category;
	
	private UserModel user;
	
	private Set<CommentModel> comments = new HashSet<>();
}
