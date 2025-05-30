package com.hexaware.easypay.dto;

public class EmpMicroDto {
	private String employeeName;
	private String employeeDepartment;
	private String position;
	private int roleId;
	
	public EmpMicroDto() {
		super();
		
	}

	public EmpMicroDto(String employeeName, String employeeDepartment, String position, int roleId) {
		super();
		this.employeeName = employeeName;
		this.employeeDepartment = employeeDepartment;
		this.position = position;
		this.roleId = roleId;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "EmpMicroDTO [employeeName=" + employeeName + ", employeeDepartment=" + employeeDepartment
				+ ", position=" + position + ", roleId=" + roleId + "]";
	}
	

}
