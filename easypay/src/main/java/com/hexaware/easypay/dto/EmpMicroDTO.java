package com.hexaware.easypay.dto;

public class EmpMicroDto {
	private String empName;
	private String empDepartment;
	private String position;
	private int roleId;
	
	
	public EmpMicroDto() {
		super();
	}


	public EmpMicroDto(String empName, String empDepartment, String position, int roleId) {
		super();
		this.empName = empName;
		this.empDepartment = empDepartment;
		this.position = position;
		this.roleId = roleId;
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


	public void setPosition(String positon) {
		this.position = positon;
	}

	
	

	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	@Override
	public String toString() {
		return "EmpDto [empName=" + empName + ", empDepartment=" + empDepartment + ", positon=" + position + "]";
	}
	
	

}
