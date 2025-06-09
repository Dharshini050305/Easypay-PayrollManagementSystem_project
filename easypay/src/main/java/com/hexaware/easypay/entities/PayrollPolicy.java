package com.hexaware.easypay.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class PayrollPolicy {
	  @Id
	    @Positive(message = "Policy ID must be a positive number.")
	    private int policyId;

	    @NotNull(message = "Policy name must not be null.")
	    private String policyName; 

	    @NotBlank(message = "Policy description must not be blank.")
	    private String policyDesc;

	    @NotNull(message = "Base salary must not be null.")
	    @DecimalMin(value = "0.0", inclusive = true, message = "Base salary must be 0 or greater.")
	    private double baseSalary;

	    @NotNull(message = "Overtime rate must not be null.")
	    @DecimalMin(value = "0.0", inclusive = true, message = "Overtime rate must be 0 or greater.")
	    private double overtimeRate;

	    @NotNull(message = "Tax percentage must not be null.")
	    @DecimalMin(value = "0.0", inclusive = true, message = "Tax percentage must be 0 or greater.")
	    @DecimalMax(value = "100.0", inclusive = true, message = "Tax percentage cannot exceed 100.")
	    private double taxPercentage;

	    
	    public PayrollPolicy() {
	        super();
	    }

	    
	    public PayrollPolicy(int policyId, String policyName, String policyDesc, double baseSalary, double overtimeRate,
	                         double taxPercentage) {
	        super();
	        this.policyId = policyId;
	        this.policyName = policyName;
	        this.policyDesc = policyDesc;
	        this.baseSalary = baseSalary;
	        this.overtimeRate = overtimeRate;
	        this.taxPercentage = taxPercentage;
	    }

	    
	    public int getPolicyId() {
	        return policyId;
	    }

	    public void setPolicyId(int policyId) {
	        this.policyId = policyId;
	    }

	    public String getPolicyName() {
	        return policyName;
	    }

	    public void setPolicyName(String policyName) {
	        this.policyName = policyName;
	    }

	    public String getPolicyDesc() {
	        return policyDesc;
	    }

	    public void setPolicyDesc(String policyDesc) {
	        this.policyDesc = policyDesc;
	    }

	    public double getBaseSalary() {
	        return baseSalary;
	    }

	    public void setBaseSalary(double baseSalary) {
	        this.baseSalary = baseSalary;
	    }

	    public double getOvertimeRate() {
	        return overtimeRate;
	    }

	    public void setOvertimeRate(double overtimeRate) {
	        this.overtimeRate = overtimeRate;
	    }

	    public double getTaxPercentage() {
	        return taxPercentage;
	    }

	    public void setTaxPercentage(double taxPercentage) {
	        this.taxPercentage = taxPercentage;
	    }


		@Override
		public String toString() {
			return "PayrollPolicy [policyId=" + policyId + ", policyName=" + policyName + ", policyDesc=" + policyDesc
					+ ", baseSalary=" + baseSalary + ", overtimeRate=" + overtimeRate + ", taxPercentage=" + taxPercentage
					+ "]";
		}
	    
	    
	}