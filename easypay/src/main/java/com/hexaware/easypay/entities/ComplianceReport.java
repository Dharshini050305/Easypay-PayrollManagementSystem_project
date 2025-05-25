package com.hexaware.easypay.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ComplianceReport {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reportId;
	
	@NotNull(message = "Report date must not be null.")
    @FutureOrPresent(message = "Report date must be the current date or a future date.")
	private LocalDate reportDate;
	
	@NotBlank(message = "Compliance status must not be blank.")
    @Size(min = 3, max = 50, message = "Compliance status must be between 3 and 50 characters.")
    @Pattern(regexp = "[A-Za-z ]+", message = "Compliance status must only contain letters and spaces.")
	private String complianceStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    @NotNull(message = "Employee reference must not be null.")
	private Employee employee;
	public ComplianceReport() {
		super();
		
	}
	public ComplianceReport(int reportId, LocalDate reportDate, String complianceStatus, Employee employee) {
		super();
		this.reportId = reportId;
		this.reportDate = reportDate;
		this.complianceStatus = complianceStatus;
		this.employee = employee;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	public String getComplianceStatus() {
		return complianceStatus;
	}
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "ComplianceReport [reportId=" + reportId + ", reportDate=" + reportDate + ", complianceStatus="
				+ complianceStatus + ", employee=" + employee + "]";
	}
	
	

}
