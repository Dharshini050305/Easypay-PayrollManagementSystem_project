package com.hexaware.easypay.exception;
/**
 * Custom exception to be thrown when an Payroll entity is not found in the system.
 * * 
 * @author Dharshini
 * @version 1.0
 */
public class PayrollNotFoundException extends RuntimeException {
	 
	private static final long serialVersionUID = 1L;

	public PayrollNotFoundException(String message) {
	        super(message);
	    }
}
