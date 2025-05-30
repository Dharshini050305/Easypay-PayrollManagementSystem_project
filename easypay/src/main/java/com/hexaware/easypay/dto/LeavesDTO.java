package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class LeavesDto {
	private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
	public LeavesDto() {
		super();
		
	}
	public LeavesDto(int employeeId, LocalDate startDate, LocalDate endDate, String leaveType) {
		super();
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
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
	@Override
	public String toString() {
		return "LeavesDTO [employeeId=" + employeeId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", leaveType=" + leaveType + "]";
	}
    
    
    

}
