package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class LeavesDto {
	
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
//    private ManagerDTO managerID;
    
    private int managerID;
    
	public LeavesDto() {
		super();
	}



	public LeavesDto(LocalDate startDate, LocalDate endDate, String leaveType, ManagerDTO managerDto) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.managerID = managerDto.getManagerId();
	}



	public int getManagerID() {
		return managerID;
	}



	public void setManagerID(ManagerDTO managerDto) {
		this.managerID = managerDto.getManagerId();
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
    
    
    
}