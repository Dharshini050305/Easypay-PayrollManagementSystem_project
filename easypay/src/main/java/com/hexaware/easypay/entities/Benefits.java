package com.hexaware.easypay.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Benefits {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int benefitId;
	
	@NotBlank(message = "Benefit name must not be blank.")
    @Size(min = 3, max = 50, message = "Benefit name must be between 3 and 50 characters.")
    @Pattern(regexp = "[A-Za-z ]+", message = "Benefit name must only contain letters and spaces.")
	private String benefitName;
	
	@Positive(message = "Benefit amount must be positive.")
    @DecimalMin(value = "0.1", inclusive = true, message = "Benefit amount must be greater than or equal to 0.1.")
	private double benefitAmount;
	 
	public Benefits() {
		super();
		
	}

	public Benefits(int benefitId,
			@NotBlank(message = "Benefit name must not be blank.") @Size(min = 3, max = 50, message = "Benefit name must be between 3 and 50 characters.") @Pattern(regexp = "[A-Za-z ]+", message = "Benefit name must only contain letters and spaces.") String benifitName,
			@Positive(message = "Benefit amount must be positive.") @DecimalMin(value = "0.1", inclusive = true, message = "Benefit amount must be greater than or equal to 0.1.") double benefitAmount) {
		super();
		this.benefitId = benefitId;
		this.benefitName = benifitName;
		this.benefitAmount = benefitAmount;
	}

	public int getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}

	public String getBenifitName() {
		return benefitName;
	}

	public void setBenefitName(String benifitName) {
		this.benefitName = benifitName;
	}

	public double getBenefitAmount() {
		return benefitAmount;
	}

	public void setBenefitAmount(double benefitAmount) {
		this.benefitAmount = benefitAmount;
	}

	@Override
	public String toString() {
		return "Benefits [benefitId=" + benefitId + ", benifitName=" + benefitName + ", benefitAmount=" + benefitAmount
				+ "]";
	}

	
	
	 

	

}
