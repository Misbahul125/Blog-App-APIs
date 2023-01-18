package com.codershub.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codershub.blog.payloads.user.ApiResponseUserModel;

@ControllerAdvice  //used for global exception handling as it checks all controllers present.
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseUserModel> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message= ex.getMessage();
		ApiResponseUserModel apiResponse=new ApiResponseUserModel(false,500,message, null);
		return new ResponseEntity<ApiResponseUserModel>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
