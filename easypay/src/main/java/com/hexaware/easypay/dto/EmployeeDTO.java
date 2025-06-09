package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class EmployeeDto {
	  private String empName;
	    private String position;
	    private String empDepartment;
	    private double empsalary;
	    private LocalDate joinDate;
	    private int userId;      
	    private int deductionId; 
	    private int benefitId;    
	    private int roleId;      
	    private Integer managerId;
	    
		public EmployeeDto() {
			super();
		}

		public EmployeeDto(String empName, String position, String empDepartment, double empsalary, LocalDate joinDate,
				int userId, int deductionId, int benefitId, int roleId, Integer managerId) {
			super();
			this.empName = empName;
			this.position = position;
			this.empDepartment = empDepartment;
			this.empsalary = empsalary;
			this.joinDate = joinDate;
			this.userId = userId;
			this.deductionId = deductionId;
			this.benefitId = benefitId;
			this.roleId = roleId;
			this.managerId = managerId;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getEmpDepartment() {
			return empDepartment;
		}

		public void setEmpDepartment(String empDepartment) {
			this.empDepartment = empDepartment;
		}

		public double getEmpsalary() {
			return empsalary;
		}

		public void setEmpsalary(double empsalary) {
			this.empsalary = empsalary;
		}

		public LocalDate getJoinDate() {
			return joinDate;
		}

		public void setJoinDate(LocalDate joinDate) {
			this.joinDate = joinDate;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getDeductionId() {
			return deductionId;
		}

		public void setDeductionId(int deductionId) {
			this.deductionId = deductionId;
		}

		public int getBenefitId() {
			return benefitId;
		}

		public void setBenefitId(int benefitId) {
			this.benefitId = benefitId;
		}

		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}

		public Integer getManagerId() {
			return managerId;
		}

		public void setManagerId(Integer managerId) {
			this.managerId = managerId;
		}

	    
	    
	}