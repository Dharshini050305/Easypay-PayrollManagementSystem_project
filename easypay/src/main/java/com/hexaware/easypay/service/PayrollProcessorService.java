package com.hexaware.easypay.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Benefits;
import com.hexaware.easypay.entity.Deductions;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.exception.BenefitsNotFoundException;
import com.hexaware.easypay.exception.DeductionNotFoundException;
import com.hexaware.easypay.exception.PayrollNotFoundException;

public interface PayrollProcessorService {
	 
		// Payroll Calculation
	 Payroll calculatePayroll(int employeeId, LocalDate payrollDate);PayrollDTO mapToDto(Payroll payroll);

	    // Payment Processing
	    Payroll processPayment(int employeeId, LocalDate payrollDate) throws PayrollNotFoundException;
	    // Benefits Management
	    Benefits addBenefit(Benefits benefit) throws BenefitsNotFoundException;
	    Benefits updateBenefit(int benefitId,Benefits benefit) throws BenefitsNotFoundException;
	    void deleteBenefit(int benefitId) throws BenefitsNotFoundException;
	    Benefits getBenefitById(int benefitId) throws BenefitsNotFoundException;
	    List<Benefits> getAllBenefits();

	    // Deductions Management
	    Deductions addDeduction(Deductions deduction) throws DeductionNotFoundException;
	    Deductions updateDeduction(int deductionId,Deductions deduction) throws DeductionNotFoundException;
	    void deleteDeduction(int deductionId) throws DeductionNotFoundException;
	    Deductions getDeductionById(int deductionId) throws DeductionNotFoundException;
	    List<Deductions> getAllDeductions();


}
