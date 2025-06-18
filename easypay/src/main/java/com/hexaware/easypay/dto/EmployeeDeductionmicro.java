package com.hexaware.easypay.dto;
public class EmployeeDeductionmicro {

	private String employeeName;
	
	private String position;
	private int deductionId;
	public EmployeeDeductionmicro() {
		super();
	}
	public EmployeeDeductionmicro(String employeeName, String position,  
			int deductionId) {
		super();
		this.employeeName = employeeName;
		this.position = position;
		
		this.deductionId = deductionId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getDeductionId() {
		return deductionId;
	}
	public void setDeductionId(int deductionId) {
		this.deductionId = deductionId;
	}
	@Override
	public String toString() {
		return "EmployeeReceiveDTO [employeeName=" + employeeName + ", position=" + position
				+ ", deductionId=" + deductionId+" ]";
	}
	

}