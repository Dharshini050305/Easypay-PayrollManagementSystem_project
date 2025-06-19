package com.hexaware.deduction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.deduction.dto.DeductionsDTO;
import com.hexaware.deduction.entity.Deductions;
import com.hexaware.deduction.repository.DeductionRepo;

@Service
public class DeductionServiceImpl implements DeductionService {

    @Autowired
    private DeductionRepo deductionsRepo;
    @Override
    public DeductionsDTO saveDeduction(DeductionsDTO dto) {
        Deductions deduction = new Deductions();
        deduction.setDeductionName(dto.getDeductionName());
        deduction.setDeductionAmount(dto.getDeductionAmount());

        Deductions saved = deductionsRepo.save(deduction);

        return new DeductionsDTO(saved.getDeductionId(), saved.getDeductionName(), saved.getDeductionAmount());
    }

    @Override
    public DeductionsDTO getDeductionById(int deductionId) {
        Deductions deduction = deductionsRepo.findById(deductionId)
                .orElseThrow(() -> new RuntimeException("Deduction not found with ID: " + deductionId));

        return new DeductionsDTO(
                deduction.getDeductionId(),
                deduction.getDeductionName(),
                deduction.getDeductionAmount()
        );
    }
}