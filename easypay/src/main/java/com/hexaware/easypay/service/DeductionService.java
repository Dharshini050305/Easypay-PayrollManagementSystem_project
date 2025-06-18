package com.hexaware.easypay.service;
import com.hexaware.easypay.dto.EmployeeDeductionVO;

public interface DeductionService {
	public EmployeeDeductionVO getEmployeeAndDeductionById(int employeeId);
}
