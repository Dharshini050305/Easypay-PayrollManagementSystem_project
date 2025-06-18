package com.hexaware.easypay.dto;

import java.time.LocalDate;
import com.hexaware.easypay.entity.Role; 

public class EmployeeDTO {

    private int employeeId;
    private String employeeName;
    private String position;
    private String email;
    private double employeeSalary;
    private LocalDate joinDate;
    private int userId;
    private Role role; 
    private Integer managerId;
    private int deductionId;
    private int benefitId;

    public EmployeeDTO() {
        super();
    }

	public EmployeeDTO(int employeeId, String employeeName, String position, String email, double employeeSalary,
			LocalDate joinDate, int userId, Role role, Integer managerId, int deductionId, int benefitId) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.position = position;
		this.email = email;
		this.employeeSalary = employeeSalary;
		this.joinDate = joinDate;
		this.userId = userId;
		this.role = role;
		this.managerId = managerId;
		this.deductionId = deductionId;
		this.benefitId = benefitId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
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

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", employeeName=" + employeeName + ", position=" + position
				+ ", email=" + email + ", employeeSalary=" + employeeSalary + ", joinDate=" + joinDate + ", userId="
				+ userId + ", role=" + role + ", managerId=" + managerId + ", deductionId=" + deductionId
				+ ", benefitId=" + benefitId + "]";
	}

	

	

    
}
