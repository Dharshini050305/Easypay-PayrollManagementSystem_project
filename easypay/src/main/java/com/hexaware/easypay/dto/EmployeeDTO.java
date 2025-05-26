package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class EmployeeDTO {
	 private String employeeName;
	    private String position;
	    private String employeeDepartment;
	    private double employeesalary;
	    private LocalDate joinDate;
	    private int userId;      
	    private int deductionId; 
	    private int benefitId;    
	    private int roleId;      
	    private Integer managerId;
	    
		public EmployeeDTO() {
			super();
			
		}

		public EmployeeDTO(String employeeName, String position, String employeeDepartment, double employeesalary,
				LocalDate joinDate, int userId, int deductionId, int benefitId, int roleId, Integer managerId) {
			super();
			this.employeeName = employeeName;
			this.position = position;
			this.employeeDepartment = employeeDepartment;
			this.employeesalary = employeesalary;
			this.joinDate = joinDate;
			this.userId = userId;
			this.deductionId = deductionId;
			this.benefitId = benefitId;
			this.roleId = roleId;
			this.managerId = managerId;
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

		public String getEmployeeDepartment() {
			return employeeDepartment;
		}

		public void setEmployeeDepartment(String employeeDepartment) {
			this.employeeDepartment = employeeDepartment;
		}

		public double getEmployeesalary() {
			return employeesalary;
		}

		public void setEmployeesalary(double employeesalary) {
			this.employeesalary = employeesalary;
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

		@Override
		public String toString() {
			return "EmployeeDTO [employeeName=" + employeeName + ", position=" + position + ", employeeDepartment="
					+ employeeDepartment + ", employeesalary=" + employeesalary + ", joinDate=" + joinDate + ", userId="
					+ userId + ", deductionId=" + deductionId + ", benefitId=" + benefitId + ", roleId=" + roleId
					+ ", managerId=" + managerId + "]";
		}
	    
	    

}
