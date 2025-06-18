package com.hexaware.easypay.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendanceId;
	
	@ManyToOne
	@JoinColumn(name = "emp_id",nullable= false)
	@NotNull(message= "Employee cannot be null")
	private Employee employee;
	
	 @NotNull(message = "Work date is required")
	private LocalDate workDate;
	
	@PositiveOrZero(message = "Hours worked cannot be negative")
	private double hoursWorked;
	
	@NotBlank(message = "Attendance status is required")
	@Pattern(regexp = "Present|Absent|Leave", message = "Attendance status must be 'Present', 'Absent', or 'Leave'")
	private String attendedStatus;

	public Attendance() {
		super();
		
	}

	public Attendance(int attendanceId, @NotNull(message = "Employee cannot be null") Employee employee,
			@NotNull(message = "Work date is required") LocalDate workDate,
			@PositiveOrZero(message = "Hours worked cannot be negative") double hoursWorked,
			@NotBlank(message = "Attendance status is required") @Pattern(regexp = "Present|Absent|Leave", message = "Attendance status must be 'Present', 'Absent', or 'Leave'") String attendedStatus) {
		super();
		this.attendanceId = attendanceId;
		this.employee = employee;
		this.workDate = workDate;
		this.hoursWorked = hoursWorked;
		this.attendedStatus = attendedStatus;
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public String getAttendedStatus() {
		return attendedStatus;
	}

	public void setAttendedStatus(String attendedStatus) {
		this.attendedStatus = attendedStatus;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", employee=" + employee + ", workDate=" + workDate
				+ ", hoursWorked=" + hoursWorked + ", attendedStatus=" + attendedStatus + "]";
	}

	
	

}

