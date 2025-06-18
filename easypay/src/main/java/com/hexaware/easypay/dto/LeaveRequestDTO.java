package com.hexaware.easypay.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;



public class LeaveRequestDTO {
   private int leaveId;
   private int employeeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String leaveType;
	@Pattern(regexp = "Pending|Approved|Rejected", message = "Leave status must be 'Pending','Rejected', or 'Approved'")
	private String leaveStatus;

	private int managerId;
	

	public LeaveRequestDTO() {
		super();
		
	}


	public LeaveRequestDTO(int leaveId, int employeeId, LocalDate startDate, LocalDate endDate, String leaveType,
			@Pattern(regexp = "Pending|Approved|Rejected", message = "Leave status must be 'Pending','Rejected', or 'Approved'") String leaveStatus,
			int managerId) {
		super();
		this.leaveId = leaveId;
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.leaveStatus = leaveStatus;
		this.managerId = managerId;
	}


	public int getLeaveId() {
		return leaveId;
	}


	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	@Override
	public String toString() {
		return "LeaveRequestDTO [leaveId=" + leaveId + ", employeeId=" + employeeId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", leaveType=" + leaveType + ", leaveStatus=" + leaveStatus + ", managerId="
				+ managerId + "]";
	}
	
}
