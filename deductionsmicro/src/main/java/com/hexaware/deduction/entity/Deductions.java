package com.hexaware.deduction.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity

@Table(name = "deductions")
public class Deductions {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int deductionId;
	    
	    @NotBlank(message = "Deduction name must not be blank.")
	    @Size(min = 5, max = 20, message = "Deduction name must be between 5 and 20 characters.")
	    @Pattern(regexp = "[A-Za-z ]+", message = "Deduction name must only contain letters and spaces.")
	    private String deductionName;
	    
	    @NotNull(message = "Deduction amount must not be null.")
	    @DecimalMin(value = "0.01", message = "Deduction amount must be greater than 0.")
	    private double deductionAmount;
	    
	    public Deductions() {}

	    public Deductions(int deductionId, String deductionName, double deductionAmount) {
	        this.deductionId = deductionId;
	        this.deductionName = deductionName;
	        this.deductionAmount = deductionAmount;
	    }

	  
	    public int getDeductionId() {
	        return deductionId;
	    }

	    public void setDeductionId(int deductionId) {
	        this.deductionId = deductionId;
	    }

	    public String getDeductionName() {
	        return deductionName;
	    }

	    public void setDeductionName(String deductionName) {
	        this.deductionName = deductionName;
	    }

	    public double getDeductionAmount() {
	        return deductionAmount;
	    }

	    public void setDeductionAmount(double deductionAmount) {
	        this.deductionAmount = deductionAmount;
	    }


	  

	    @Override
	    public String toString() {
	        return "Deductions [deductionId=" + deductionId + ", deductionName=" + deductionName + 
	               ", deductionAmount=" + deductionAmount + ", employee=" +  "]";
	    }
	}
	    