package com.hexaware.easypay.exception;
/**
 * Custom exception to be thrown when an users entity is not found in the system.
 * * 
 * @author Dharshini
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
