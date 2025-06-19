package com.hexaware.easypay.exception;
/**
 * Custom exception to be thrown when an Deductions entity is not found in the system.
 * * 
 * @author Dharshini
 * @version 1.0
 */
public class DeductionNotFoundException  extends RuntimeException{
	
	private static final long serialVersionUID = 11L;

	public DeductionNotFoundException(String message) {
		super();
	}
}
