package com.hexaware.easypay.dto;

public class EmpDto {
	private String empName;
	private String empDepartment;
	private String position;
	
	
	public EmpDto() {
		super();
	}


	public EmpDto(String empName, String empDepartment, String position) {
		super();
		this.empName = empName;
		this.empDepartment = empDepartment;
		this.position = position;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getEmpDepartment() {
		return empDepartment;
	}


	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "EmpDto [empName=" + empName + ", empDepartment=" + empDepartment + ", positon=" + position + "]";
	}
	
	

}