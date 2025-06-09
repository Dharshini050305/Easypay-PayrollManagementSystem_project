package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class ComplianceReportDTO {
	private LocalDate reportDate;
    private String complianceStatus;
    private int  emp_id;
	public ComplianceReportDTO() {
		super();
	}
	public ComplianceReportDTO(LocalDate reportDate, String complianceStatus, int emp_id) {
		super();
		this.reportDate = reportDate;
		this.complianceStatus = complianceStatus;
		this.emp_id = emp_id;
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
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	@Override
	public String toString() {
		return "ComplianceReportDTO [reportDate=" + reportDate + ", complianceStatus=" + complianceStatus + ", emp_id="
				+ emp_id + "]";
	}
    
    
	
}