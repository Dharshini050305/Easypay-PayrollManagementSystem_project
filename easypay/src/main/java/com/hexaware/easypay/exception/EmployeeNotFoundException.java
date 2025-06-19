package com.hexaware.easypay.exception;
/**
 * Custom exception to be thrown when an Employee entity is not found in the system.
 * * 
 * @author Dharshini
 * @version 1.0
 */
public class EmployeeNotFoundException extends RuntimeException {
    
    
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String message) {
        super(message); 
    }
}
