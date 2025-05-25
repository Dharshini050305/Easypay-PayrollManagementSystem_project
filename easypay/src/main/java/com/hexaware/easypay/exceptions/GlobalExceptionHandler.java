package com.hexaware.easypay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(LeavesRequestException.class)
	public ResponseEntity<String> handleLeavesRequestException (LeavesRequestException e){
		return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);	
	}

}
