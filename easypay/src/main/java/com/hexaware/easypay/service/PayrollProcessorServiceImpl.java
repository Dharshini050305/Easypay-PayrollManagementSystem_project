package com.hexaware.easypay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Attendance;
import com.hexaware.easypay.entity.Benefits;
import com.hexaware.easypay.entity.Deductions;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.Payroll;

import com.hexaware.easypay.exception.BenefitsNotFoundException;
import com.hexaware.easypay.exception.DeductionNotFoundException;
import com.hexaware.easypay.exception.EmployeeNotFoundException;
import com.hexaware.easypay.exception.PayrollNotFoundException;
import com.hexaware.easypay.repository.AttendanceRepo;
import com.hexaware.easypay.repository.BenefitsRepo;
import com.hexaware.easypay.repository.DeductionsRepo;
import com.hexaware.easypay.repository.EmployeeRepo;
import com.hexaware.easypay.repository.PayrollRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollProcessorServiceImpl implements PayrollProcessorService {

    @Autowired
    private PayrollRepo payrollRepo;

    @Autowired
    private BenefitsRepo benefitsRepo;

    @Autowired
    private DeductionsRepo deductionsRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    // Payroll Calculation
    @Override
    public Payroll calculatePayroll(int employeeId, LocalDate payrollDate) throws EmployeeNotFoundException {
        Payroll payroll = new Payroll();

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + employeeId));

        payroll.setEmployee(employee);
        payroll.setPayrollDate(payrollDate);

        List<Attendance> monthlyAttendance = attendanceRepo.findByEmployeeIdAndMonth(employeeId, payrollDate.getMonthValue());

        int totalDaysInMonth = payrollDate.lengthOfMonth();
        int presentDays = monthlyAttendance.size();
        int absentDays = totalDaysInMonth - presentDays;

        double perDaySalary = employee.getEmployeeSalary() / totalDaysInMonth;
        double salaryAfterAbsence = employee.getEmployeeSalary() - (absentDays * perDaySalary);
        double grossPay = salaryAfterAbsence;

        Double benefitAmount = benefitsRepo.findBenefitAmountByEmployeeId(employeeId);
        Double deductionAmount = deductionsRepo.findDeductionAmountByEmployeeId(employeeId);

        double benefits = (benefitAmount != null) ? benefitAmount : 0;
        double deductions = (deductionAmount != null) ? deductionAmount : 0;

        payroll.setGrossPay(grossPay);
        payroll.setBenefits(benefits);
        payroll.setDeductions(deductions);
        payroll.setNetPay(grossPay + benefits - deductions);

        return payroll;
    }

    public PayrollDTO mapToDto(Payroll payroll) {
        return new PayrollDTO(
            payroll.getPayrollId(),
            payroll.getBenefits(),
            payroll.getDeductions(),
            payroll.getGrossPay(),
            payroll.getNetPay(),
            payroll.getPayrollDate(),
            payroll.getEmployee().getEmployeeId()
        );
    }


    // Payment Processing
    @Override
    public Payroll processPayment(int employeeId, LocalDate payrollDate)
            throws EmployeeNotFoundException, PayrollNotFoundException {

        Payroll payroll = calculatePayroll(employeeId, payrollDate);
        

        payrollRepo.save(payroll);
        System.out.println("Payroll processed successfully and saved for Employee ID: " + employeeId);

        return payroll;
    }

    // Benefits Management
    @Override
    public Benefits addBenefit(Benefits benefit) {
        return benefitsRepo.save(benefit);
    }

    @Override
    public Benefits updateBenefit(int benefitId, Benefits benefit) throws BenefitsNotFoundException {
        Benefits existingBenefit = benefitsRepo.findById(benefitId)
                .orElseThrow(() -> new BenefitsNotFoundException("Benefit not found with ID: " + benefitId));

        // Update existing benefit fields from the provided 'benefit' object
        existingBenefit.setBenefitName(benefit.getBenefitName());
        existingBenefit.setBenefitAmount(benefit.getBenefitAmount());
        // Set other fields as needed based on your Benefits entity

        return benefitsRepo.save(existingBenefit);
    }

    @Override
    public void deleteBenefit(int benefitId) throws BenefitsNotFoundException {
        if (!benefitsRepo.existsById(benefitId)) {
            throw new BenefitsNotFoundException("Benefit not found with ID: " + benefitId);
        }
        benefitsRepo.deleteById(benefitId);
    }

    @Override
    public Benefits getBenefitById(int benefitId) throws BenefitsNotFoundException {
        return benefitsRepo.findById(benefitId)
                .orElseThrow(() -> new BenefitsNotFoundException("Benefit not found with ID: " + benefitId));
    }

    @Override
    public List<Benefits> getAllBenefits() {
        return benefitsRepo.findAll();
    }

    // Deductions Management
    @Override
    public Deductions addDeduction(Deductions deduction) {
        return deductionsRepo.save(deduction);
    }

    @Override
    public Deductions updateDeduction(int deductionId, Deductions deduction) throws DeductionNotFoundException {
        Deductions existingDeduction = deductionsRepo.findById(deductionId)
                .orElseThrow(() -> new DeductionNotFoundException("Deduction not found with ID: " + deductionId));

        // Update existing deduction fields from the provided 'deduction' object
        existingDeduction.setDeductionName(deduction.getDeductionName());
        existingDeduction.setDeductionAmount(deduction.getDeductionAmount());
        // Set other fields as needed based on your Deductions entity

        return deductionsRepo.save(existingDeduction);
    }

    @Override
    public void deleteDeduction(int deductionId) throws DeductionNotFoundException {
        if (!deductionsRepo.existsById(deductionId)) {
            throw new DeductionNotFoundException("Deduction not found with ID: " + deductionId);
        }
        deductionsRepo.deleteById(deductionId);
    }

    @Override
    public Deductions getDeductionById(int deductionId) throws DeductionNotFoundException {
        return deductionsRepo.findById(deductionId)
                .orElseThrow(() -> new DeductionNotFoundException("Deduction not found with ID: " + deductionId));
    }

    @Override
    public List<Deductions> getAllDeductions() {
        return deductionsRepo.findAll();
    }

}