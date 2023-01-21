package com.codershub.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codershub.blog.payloads.user.ApiResponseUserModel;

@RestControllerAdvice  //used for global exception handling as it checks all controllers present.
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseUserModel> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		ApiResponseUserModel apiResponse = new ApiResponseUserModel(false, HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
		return new ResponseEntity<ApiResponseUserModel>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
