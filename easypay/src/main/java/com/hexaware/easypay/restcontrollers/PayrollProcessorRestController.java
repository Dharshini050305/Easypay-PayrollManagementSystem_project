package com.hexaware.easypay.restcontrollers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.entities.Benefits;
import com.hexaware.easypay.entities.Deductions;
import com.hexaware.easypay.entities.Payroll;
import com.hexaware.easypay.services.IPayrollProcessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payroll")
public class PayrollProcessorRestController {
	@Autowired
    private IPayrollProcessorService service;

    // Payroll Calculation
    @PostMapping("/calculate/{empId}")
    public Payroll calculatePayroll(@PathVariable int empId, @Valid @RequestBody LocalDate payrollDate) {
        return service.calculatePayroll(empId, payrollDate);
    }

    // Payroll Verification
    @PostMapping("/verify")
    public boolean verifyPayrollData(@Valid @RequestBody Payroll payroll) {
        return service.verifyPayrollData(payroll);
    }

    // Benefits Management
    @PostMapping("/benefits/add")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public Benefits addBenefit(@Valid @RequestBody Benefits benefit) {
        return service.addBenefit(benefit);
    }

    @PutMapping("/benefits/update")
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

    @PutMapping("/deductions/update")
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

    // Payment Processing
    @PostMapping("payment/process/{empId}/{payrollDate}")
    @PreAuthorize("hasAuthority('PAYROLL_PROCESSOR')")
    public String processPayment(@PathVariable int empId, @PathVariable String payrollDate) {
        try {
            // Convert payrollDate (String) to LocalDate
            LocalDate date = LocalDate.parse(payrollDate);
            service.processPayment(empId, date);
            return "Payroll processed successfully for Employee ID: " + empId;
        } catch (Exception e) {
            return "Error processing payroll: " + e.getMessage();
        }
    }
}
