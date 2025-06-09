package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class AttendanceDto {
	private int empId;
	private LocalDate workDate;
	private double hoursWorked;
	private String status;
	
	public AttendanceDto() {
		super();
	}

	public AttendanceDto(int empId, LocalDate workDate, double hoursWorked, String status) {
		super();
		this.empId = empId;
		this.workDate = workDate;
		this.hoursWorked = hoursWorked;
		this.status = status;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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
	
	
}