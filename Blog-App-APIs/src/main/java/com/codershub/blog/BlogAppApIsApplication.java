package com.codershub.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApIsApplication.class, args);
		
		System.out.println("Application Started Successfully !!");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
