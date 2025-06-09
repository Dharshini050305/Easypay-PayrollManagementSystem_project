package com.hexaware.easypay.dto;

public class ManagerDTO {
	 private int managerId ;
	 private String empName;
	
	 private String empDepartment;
	 
	 
	public ManagerDTO() {
		super();
	}
	
	
	public ManagerDTO(int managerId, String empName, String position, String empDepartment) {
		super();
		this.managerId = managerId;
		this.empName = empName;
		
		this.empDepartment = empDepartment;
	}


	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
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

	 
	 

}
