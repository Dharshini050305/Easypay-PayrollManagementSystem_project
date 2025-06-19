package com.hexaware.easypay.exception;
/**
 * Custom exception to be thrown when an Benefits entity is not found in the system.
 * * 
 * @author Dharshini
 * @version 1.0
 */
public class BenefitsNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 11L;

	public BenefitsNotFoundException(String message) {
		super(message);
	}
}
