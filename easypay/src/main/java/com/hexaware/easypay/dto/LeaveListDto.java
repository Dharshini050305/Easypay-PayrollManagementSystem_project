package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class LeaveListDto {
	private int empId;
	private int leaveId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String leaveType;
	private String leaveStatus ;
//	    private ManagerDTO managerID;

	private int managerId;

	public LeaveListDto() {
		super();
	}

	public LeaveListDto(LocalDate startDate, LocalDate endDate, String leaveType, int managerId, int empId, String leaveStatus, int leaveId) {
		super();
		this.empId = empId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.managerId = managerId;
		this.leaveStatus= leaveStatus;
		this.leaveId=leaveId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getManagerID() {
		return managerId;
	}

	public void setManagerID(int managerId) {
		this.managerId = managerId;
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

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

}


