package com.hexaware.easypay.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Benefits;
import com.hexaware.easypay.entity.Deductions;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.service.PayrollProcessorServiceImpl;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payroll")
public class PayrollController {
	@Autowired
    private PayrollProcessorServiceImpl service;

	 // Payment Processing
    @PostMapping("/payment/process/{employeeId}/{payrollDate}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public ResponseEntity<Map<String, Object>> processPayment(
            @PathVariable int employeeId,
            @PathVariable String payrollDate) {
        try {
            LocalDate date = LocalDate.parse(payrollDate);
            Payroll payroll = service.processPayment(employeeId, date);  // Save + return

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payroll processed successfully");
            response.put("grossPay", payroll.getGrossPay());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Error processing payroll: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

//calculate payroll
@GetMapping("/calculate/{employeeId}/{payrollDate}")
@PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
public PayrollDTO calculatePayroll(@PathVariable int employeeId, @PathVariable String payrollDate) {
    LocalDate date = LocalDate.parse(payrollDate);
    Payroll payroll = service.calculatePayroll(employeeId, date);  // Returns full Payroll with Employee

  
    PayrollDTO dto = new PayrollDTO();
    dto.setPayrollId(payroll.getPayrollId());
    dto.setBenefits(payroll.getBenefits());
    dto.setDeductions(payroll.getDeductions());
    dto.setGrossPay(payroll.getGrossPay());
    dto.setNetPay(payroll.getNetPay());
    dto.setPayrollDate(payroll.getPayrollDate());
    dto.setEmployeeId(payroll.getEmployee().getEmployeeId());

    return dto;
}
    
    //Benefits Management
    @PostMapping("/benefits/add")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public Benefits addBenefit(@Valid @RequestBody Benefits benefit) {
        return service.addBenefit(benefit);
    }

    @PutMapping("/benefits/update/{benefitId}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public Benefits updateBenefit(@Valid @PathVariable int benefitId, @RequestBody Benefits benefit) {
        return service.updateBenefit(benefitId,benefit);
    }

    @DeleteMapping("/benefits/delete/{benefitId}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public String deleteBenefit(@PathVariable int benefitId) {
        service.deleteBenefit(benefitId);
        return "Benefit record deleted for BenefitId " + benefitId;
    }

    @GetMapping("/benefits/{benefitId}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public Benefits getBenefitById(@PathVariable int benefitId) {
        return service.getBenefitById(benefitId);
    }

    @GetMapping("/benefits/all")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public List<Benefits> getAllBenefits() {
        return service.getAllBenefits();
    }

    // Deductions Management
    @PostMapping("/deductions/add")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public Deductions addDeduction(@Valid @RequestBody Deductions deduction) {
        return service.addDeduction(deduction);
    }

    @PutMapping("/deductions/update/{deductionId}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public Deductions updateDeduction(@Valid @PathVariable int deductionId,@RequestBody Deductions deduction) {
        return service.updateDeduction(deductionId,deduction);
    }

    @DeleteMapping("/deductions/delete/{deductionId}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public String deleteDeduction(@PathVariable int deductionId) {
        service.deleteDeduction(deductionId);
        return "Deduction record deleted for DeductionId " + deductionId;
    }

    @GetMapping("/deductions/{deductionId}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public Deductions getDeductionById(@PathVariable int deductionId) {
        return service.getDeductionById(deductionId);
    }

    @GetMapping("/deductions/all")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public List<Deductions> getAllDeductions() {
        return service.getAllDeductions();
    }

}