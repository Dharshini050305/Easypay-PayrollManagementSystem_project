package com.hexaware.easypay.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.entities.Attendance;
import com.hexaware.easypay.entities.Benefits;
import com.hexaware.easypay.entities.Deductions;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.Payroll;
import com.hexaware.easypay.entities.PayrollPolicy;
import com.hexaware.easypay.exceptions.PayrollNotFoundException;
import com.hexaware.easypay.repositories.AttendanceRepository;
import com.hexaware.easypay.repositories.BenefitsRepository;
import com.hexaware.easypay.repositories.DeductionsRepository;
import com.hexaware.easypay.repositories.EmployeeRepository;
import com.hexaware.easypay.repositories.PayrollPolicyRepository;
import com.hexaware.easypay.repositories.PayrollRepository;

@Service
public class PayrollProcessorServiceImpl implements IPayrollProcessorService {
	 @Autowired
	    private PayrollRepository payrollRepo;

	    @Autowired
	    private PayrollPolicyRepository payrollPolicyRepo;

	    @Autowired
	    private BenefitsRepository benefitsRepo;

	    @Autowired
	    private DeductionsRepository deductionsRepo;

	    @Autowired
	    private EmployeeRepository employeeRepo;

	    @Autowired
	    private AttendanceRepository attendanceRepo;

	    // Payroll Calculation
	    @Override
	    public Payroll calculatePayroll(int employeeId, LocalDate payrollDate) {
	        Payroll payroll = new Payroll();
	        Employee employee = employeeRepo.findById(employeeId)
	            .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
	        payroll.setEmployee(employee);
	        payroll.setPayrollDate(payrollDate);
	 
	        // Get attendance for the month
	        List<Attendance> monthlyAttendance = attendanceRepo.findByEmployeeIdAndMonth(employeeId, payrollDate.getMonthValue());
	        
	        // Calculate present days and salary deductions
	        int totalDaysInMonth = payrollDate.lengthOfMonth();
	        int presentDays = monthlyAttendance.size();
	        int absentDays = totalDaysInMonth - presentDays;
	        double perDaySalary = employee.getEmployeeSalary() / totalDaysInMonth;
	        double salaryAfterAbsence = employee.getEmployeeSalary() - (absentDays * perDaySalary);
	        
	        // Calculate overtime
	        double totalOvertimeHours = 0;
	        for (Attendance att : monthlyAttendance) {
	            if (att.getHoursWorked() > 8) {
	                totalOvertimeHours += att.getHoursWorked() - 8;
	            }
	        }

	        PayrollPolicy policy = payrollPolicyRepo.findById(employeeId)
	            .orElseThrow(() -> new RuntimeException("Payroll policy not found for employee ID: " + employeeId));
	        double overtimePay = totalOvertimeHours * policy.getOvertimeRate();
	        
	        // Fetch benefits using the repository method
	        Double benefitAmount = benefitsRepo.findBenefitAmountByEmployeeId(employeeId);
	        
	        // If benefitAmount is not null, add it to the payroll
	        double totalBenefits = (benefitAmount != null) ? benefitAmount : 0;

	        Double totalDeductions = deductionsRepo.findDeductionAmountByEmployeeId(employeeId);


	        

	        
	        // Set final calculations
	        payroll.setGrossPay(salaryAfterAbsence + overtimePay);
	        payroll.setBenefits(totalBenefits);
	        payroll.setDeductions(totalDeductions);
	        payroll.setNetPay(payroll.getGrossPay() + totalBenefits - totalDeductions);
	        
	        return payroll;
	    }
	    

	    
	    @Override
	    public boolean verifyPayrollData(Payroll payroll) {
	        if (payroll == null) {
	            System.out.println("Payroll data is null.");
	            return false;
	        }

	        // Check if Employee exists
	        Employee employee = payroll.getEmployee();
	        if (employee == null || employeeRepo.findById(employee.getEmployeeId()).isEmpty()) {
	            System.out.println("Employee not found for ID: " + (employee != null ? employee.getEmployeeId() : "null"));
	            return false;
	        }

	        // Validate gross pay, deductions, and benefits
	        if (payroll.getGrossPay() <= 0) {
	            System.out.println("Invalid gross pay.");
	            return false;
	        }
	        if (payroll.getDeductions() < 0 || payroll.getBenefits() < 0) {
	            System.out.println("Invalid deductions or benefits.");
	            return false;
	        }

	        // Validate payroll date
	        if (payroll.getPayrollDate() == null || payroll.getPayrollDate().isAfter(LocalDate.now())) {
	            System.out.println("Invalid payroll date.");
	            return false;
	        }

	        System.out.println("Payroll data verification successful for Employee ID: " + payroll.getEmployee().getEmployeeId());
	        return true;
	    }
	    
	    // Benefits Management
	    @Override
	    public Benefits addBenefit(Benefits benefit) {
	        return benefitsRepo.save(benefit);
	    }

	    @Override
	    public Benefits updateBenefit(int benefitId,Benefits benefit) {
	        return benefitsRepo.save(benefit);
	    }

	    @Override
	    public void deleteBenefit(int benefitId) {
	        benefitsRepo.deleteById(benefitId);
	    }

	    @Override
	    public Benefits getBenefitById(int benefitId) {
	        return benefitsRepo.findById(benefitId).orElse(null);
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
	    public Deductions updateDeduction(int deductionId,Deductions deduction) {
	        return deductionsRepo.save(deduction);
	    }

	    @Override
	    public void deleteDeduction(int deductionId) {
	        deductionsRepo.deleteById(deductionId);
	    }

	    @Override
	    public Deductions getDeductionById(int deductionId) {
	        return deductionsRepo.findById(deductionId).orElse(null);
	    }

	    @Override
	    public List<Deductions> getAllDeductions() {
	        return deductionsRepo.findAll();
	    }

	    // Payment Processing
	    public void processPayroll(int employeeId, LocalDate payrollDate) {

	    }



		@Override
		public void processPayment(int employeeId, LocalDate payrollDate) throws PayrollNotFoundException {
	        // Step 1: Calculate payroll
	        Payroll payroll = calculatePayroll(employeeId, payrollDate);

	        // Step 2: Verify payroll data
	        if (!verifyPayrollData(payroll)) {
	            System.out.println("Payroll verification failed. Aborting payroll process.");
	            return;
	        }

	        // Step 3: Save to database
	        payrollRepo.save(payroll);
	        System.out.println("Payroll processed successfully and saved for Employee ID: " + employeeId);	
			
			
		}

}
