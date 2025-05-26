package com.hexaware.easypay.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}

}
