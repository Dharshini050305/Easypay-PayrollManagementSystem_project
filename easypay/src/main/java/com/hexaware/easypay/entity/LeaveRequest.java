package com.hexaware.easypay.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity

@Table(name ="leaverequest")
public class LeaveRequest {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveId;
	
	@ManyToOne
	@JoinColumn(name= "emp_id" ,nullable = false)
	private Employee employee;
	
	
	private LocalDate startDate;
	
	
	private LocalDate endDate;
	
	@NotBlank(message="LeaveType should not be null")
	private String leaveType;
	
	@NotBlank(message="LeaveStatus should not be null")
	@Pattern(regexp = "Pending|Approved", message = "Leave status must be 'Pending', or 'Approved'")
	private String leaveStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name= "manager_id")
	private Employee manager;

	public LeaveRequest() {
		super();
	}

	

	public LeaveRequest(int leaveId, Employee employee, LocalDate startDate, LocalDate endDate,
			@NotBlank(message = "LeaveType should not be null") String leaveType,
			@NotBlank(message = "LeaveStatus should not be null") @Pattern(regexp = "Pending|Approved", message = "Leave status must be 'Pending', or 'Approved'") String leaveStatus, Employee manager) {
		super();
		this.leaveId = leaveId;
		this.employee = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.leaveStatus = leaveStatus;
		this.manager = manager;
	}



	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "LeaveRequest [leaveId=" + leaveId + ", employee=" + employee + ", startDate=" + startDate + ", endDate="
				+ endDate + ", leaveType=" + leaveType + ", leaveStatus=" + leaveStatus + ", manager=" + manager + "]";
	}
	
	
	

}