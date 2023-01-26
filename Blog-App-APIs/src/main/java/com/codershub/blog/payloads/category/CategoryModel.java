package com.codershub.blog.payloads.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryModel {
	
	
	private Integer categoryId;
	@NotBlank
	@Size(min = 4,message ="min size is 4")
	private String CategoryTitle;
	
	@NotBlank
	@Size(min = 10,message="Min size is 10")
	private String categoryDescription;
}
