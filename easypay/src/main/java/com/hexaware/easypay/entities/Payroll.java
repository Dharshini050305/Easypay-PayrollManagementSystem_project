package com.hexaware.easypay.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Payroll {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payrollId;
	
	@NotNull(message = "Gross pay must not be null.")
	@DecimalMin(value = "0.0", inclusive = true, message = "Gross pay must be 0 or greater.")
	private double grossPay;
	
	@NotNull(message = "Benefits must not be null.")
	@DecimalMin(value = "0.0", inclusive = true, message = "Benefits must be 0 or greater.")
	private double benefits;
	
	@NotNull(message = "Deductions must not be null.")
	@DecimalMin(value = "0.0", inclusive = true, message = "Deductions must be 0 or greater.")
	private double deductions;
	
	@NotNull(message = "Payroll date must not be null.")
    @PastOrPresent(message = "Payroll date cannot be in the future.")
	private LocalDate payrollDate;
	
	
	private double netPay;
	
	@ManyToOne
    @JoinColumn(name = "empId", nullable = false) 
	private Employee employee;
	public Payroll() {
		super();
		
	}
	public Payroll(int payrollId, double grossPay, double benefits, double deductions, LocalDate payrollDate,
			double netPay, Employee employee) {
		super();
		this.payrollId = payrollId;
		this.grossPay = grossPay;
		this.benefits = benefits;
		this.deductions = deductions;
		this.payrollDate = payrollDate;
		this.netPay = netPay;
		this.employee = employee;
	}
	public int getPayrollId() {
		return payrollId;
	}
	public void setPayrollId(int payrollId) {
		this.payrollId = payrollId;
	}
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
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
	public LocalDate getPayrollDate() {
		return payrollDate;
	}
	public void setPayrollDate(LocalDate payrollDate) {
		this.payrollDate = payrollDate;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Payroll [payrollId=" + payrollId + ", grossPay=" + grossPay + ", benefits=" + benefits + ", deductions="
				+ deductions + ", payrollDate=" + payrollDate + ", netPay=" + netPay + ", employee=" + employee + "]";
	}
	
	
	

}
