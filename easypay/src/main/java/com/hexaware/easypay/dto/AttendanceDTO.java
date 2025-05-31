package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class AttendanceDto {
	private int employeeId;
	private LocalDate workDate;
	private double hoursWorked;
	private String status;
	
	public AttendanceDto() {
		super();
		
	}

	public AttendanceDto(int employeeId, LocalDate workDate, double hoursWorked, String status) {
		super();
		this.employeeId = employeeId;
		this.workDate = workDate;
		this.hoursWorked = hoursWorked;
		this.status = status;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AttendanceDto [employeeId=" + employeeId + ", workDate=" + workDate + ", hoursWorked=" + hoursWorked
				+ ", status=" + status + "]";
	}

	
	
	

}
