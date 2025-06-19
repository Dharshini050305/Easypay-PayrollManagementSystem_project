package com.hexaware.deduction.service;

import com.hexaware.deduction.dto.DeductionsDTO;

public interface DeductionService {
	
	DeductionsDTO getDeductionById(int deductionId);
	DeductionsDTO saveDeduction(DeductionsDTO dto);
	}


