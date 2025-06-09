package com.hexaware.easypay.dto;

import java.time.LocalDate;

public class ComplianceReportReceiveDTO {
	 private int reportId;
	    private LocalDate reportDate;
	    private String complianceStatus;
	    private int  emp_id;
	    
		public ComplianceReportReceiveDTO() {
			super();
		}

		public ComplianceReportReceiveDTO(int reportId, LocalDate reportDate, String complianceStatus, int emp_id) {
			super();
			this.reportId = reportId;
			this.reportDate = reportDate;
			this.complianceStatus = complianceStatus;
			this.emp_id = emp_id;
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

		public int getEmp_id() {
			return emp_id;
		}

		public void setEmp_id(int emp_id) {
			this.emp_id = emp_id;
		}

		@Override
		public String toString() {
			return "ComplianceReportDTO [reportId=" + reportId + ", reportDate=" + reportDate + ", complianceStatus="
					+ complianceStatus + ", emp_id=" + emp_id + "]";
		}
	  
	    
	    

	}