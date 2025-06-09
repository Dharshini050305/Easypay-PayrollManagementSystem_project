package com.hexaware.easypay.dto;

import java.time.LocalDate;

import com.hexaware.easypay.entities.Payroll;

public class PayrollDTO {

	private int payrollId;
	private double grossPay;
	private double benifits;
	private double deductions;
	private LocalDate payrollDate;
	private double netPay;
	private int empId;
	private String empName;
	private String position;
	private String empDepartment;
	
	public PayrollDTO(Payroll payroll) {
		this.payrollId = payroll.getPayrollId();
        this.grossPay = payroll.getGrossPay();
        this.benifits = payroll.getBenefits();
        this.deductions = payroll.getDeductions();
        this.payrollDate = payroll.getPayrollDate();
        this.netPay = payroll.getNetPay();
        this.empId = payroll.getEmployee().getEmpId();
        this.empName = payroll.getEmployee().getEmpName();
        this.position = payroll.getEmployee().getPosition();
        this.empDepartment = payroll.getEmployee().getEmpDepartment();		
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

	public double getBenifits() {
		return benifits;
	}

	public void setBenifits(double benifits) {
		this.benifits = benifits;
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

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}
	
	
	
}