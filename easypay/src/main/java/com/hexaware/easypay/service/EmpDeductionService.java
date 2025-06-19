package com.hexaware.easypay.service;
/**
 * Service imterface for managing EmployeeDeductionMicro operations in the Payroll Management System.
 * @author Dharshini
 * @version 1.0
 */
import com.hexaware.easypay.dto.EmployeeDeductionVO;

public interface EmpDeductionService {
	EmployeeDeductionVO getDeductionByEmployeeId(int employeeId);

}
