package com.hexaware.easypay.dto;

import java.time.LocalDate;


public class PayrollDTO {
	
        private int payrollId;
	    private double benefits;
	    private double deductions;
	    private double grossPay;
	    private double netPay;
	    private LocalDate payrollDate;
	    private int employeeId;
	    
		public PayrollDTO() {
			super();
	
		}

		public PayrollDTO(int payrollId, double benefits, double deductions, double grossPay, double netPay,
				LocalDate payrollDate, int employeeId) {
			super();
			this.payrollId = payrollId;
			this.benefits = benefits;
			this.deductions = deductions;
			this.grossPay = grossPay;
			this.netPay = netPay;
			this.payrollDate = payrollDate;
			this.employeeId = employeeId;
		
		}
		

		public int getPayrollId() {
			return payrollId;
		}

		public void setPayrollId(int payrollId) {
			this.payrollId = payrollId;
		}

		public double getBenefits() {
			return benefits;
		}

		public void setBenefits(double benefits) {
			this.benefits = benefits;
		}

		public double getDeductions() {
			return deductions;
		}

		public void setDeductions(double deductions) {
			this.deductions = deductions;
		}

		public double getGrossPay() {
			return grossPay;
		}

		public void setGrossPay(double grossPay) {
			this.grossPay = grossPay;
		}

		public double getNetPay() {
			return netPay;
		}

		public void setNetPay(double netPay) {
			this.netPay = netPay;
		}

		public LocalDate getPayrollDate() {
			return payrollDate;
		}

		public void setPayrollDate(LocalDate payrollDate) {
			this.payrollDate = payrollDate;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}

		@Override
		public String toString() {
			return "PayrollDTO [payrollId=" + payrollId + ", benefits=" + benefits + ", deductions=" + deductions
					+ ", grossPay=" + grossPay + ", netPay=" + netPay + ", payrollDate=" + payrollDate + ", employeeId="
					+ employeeId + "]";
		}
		
}