package com.hexaware.easypay.dto;

public class EmpDTO {
	private String employeeName;
	private String employeeDepartment;
	private String position;
	
	public EmpDTO() {
		super();
		
	}

	public EmpDTO(String employeeName, String employeeDepartment, String position) {
		super();
		this.employeeName = employeeName;
		this.employeeDepartment = employeeDepartment;
		this.position = position;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "EmpDTO [employeeName=" + employeeName + ", employeeDepartment=" + employeeDepartment + ", position="
				+ position + "]";
	}
	
	

}
