package com.hexaware.easypay.dto;

public class EmployeeDeductionVO {
	private EmployeeDTO employee;
    private DeductionsDTO deduction;

    public EmployeeDeductionVO() {}

    public EmployeeDeductionVO(EmployeeDTO employee, DeductionsDTO deduction) {
        this.employee = employee;
        this.deduction = deduction;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public DeductionsDTO getDeduction() {
        return deduction;
    }

    public void setDeduction(DeductionsDTO deduction) {
        this.deduction = deduction;
    }

    @Override
    public String toString() {
        return "EmployeeDeductionVO{" +
                "employee=" + employee +
                ", deduction=" + deduction +
                '}';
    }
}