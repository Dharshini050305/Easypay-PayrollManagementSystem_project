package com.hexaware.easypay.dto;
import com.hexaware.easypay.entity.Deductions;


public class EmployeeDeductionVO {
	private  EmployeeDeductionmicro employee;
	private  Deductions deduction;
	
	public EmployeeDeductionVO(EmployeeDeductionmicro employee, Deductions deduction) {
		super();
		this.employee = employee;
		this.deduction = deduction;
	}


	public EmployeeDeductionmicro getEmployee() {
		return employee;
	}


	public void setEmployee(EmployeeDeductionmicro employee) {
		this.employee = employee;
	}


	public Deductions getDeduction() {
		return deduction;
	}


	public void setDeduction(Deductions deduction) {
		this.deduction = deduction;
	}


	@Override
	public String toString() {
		return "EmployeeDeductionVO [employee=" + employee + ", deduction=" + deduction + "]";
	}
	
	

}
