package com.hexaware.easypay.exception;

/**
 * Custom exception to be thrown when an LeaveRequest entity is not found in the system.
 * * 
 * @author Dharshini
 * @version 1.0
 */
public class LeaveRequestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

public LeaveRequestNotFoundException(String message) {
		
		super(message);
	}

}

