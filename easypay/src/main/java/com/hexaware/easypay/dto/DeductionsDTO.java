package com.hexaware.easypay.dto;

public class DeductionsDTO {

    private int deductionId;
    private String deductionName;
    private double deductionAmount;
    
	public DeductionsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeductionsDTO(int deductionId, String deductionName, double deductionAmount) {
		super();
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
		return "DeductionsDTO [deductionId=" + deductionId + ", deductionName=" + deductionName + ", deductionAmount="
				+ deductionAmount + "]";
	}

    
}


