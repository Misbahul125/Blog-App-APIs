package com.codershub.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BlogAppApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApIsApplication.class, args);
		
		System.out.println("Application Started Successfully !!");
	}

}
