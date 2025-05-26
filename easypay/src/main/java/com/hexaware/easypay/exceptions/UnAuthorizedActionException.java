package com.hexaware.easypay.exceptions;

public class UnAuthorizedActionException extends RuntimeException{
	public UnAuthorizedActionException(String message) {
		super(message);
	}
}
