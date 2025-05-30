package com.hexaware.easypay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.EmployeeDto;
import com.hexaware.easypay.entities.Benefits;
import com.hexaware.easypay.entities.ComplianceReport;
import com.hexaware.easypay.entities.Deductions;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.PayrollPolicy;
import com.hexaware.easypay.entities.Role;
import com.hexaware.easypay.entities.User;
import com.hexaware.easypay.exceptions.EmployeeNotFoundException;
import com.hexaware.easypay.repositories.BenefitsRepository;
import com.hexaware.easypay.repositories.ComplianceReportRepository;
import com.hexaware.easypay.repositories.DeductionsRepository;
import com.hexaware.easypay.repositories.EmployeeRepository;
import com.hexaware.easypay.repositories.PayrollPolicyRepository;
import com.hexaware.easypay.repositories.RoleRepository;
import com.hexaware.easypay.repositories.UserRepository;

@Service
public class AdminHrManagerServiceImpl implements IAdminHrManagerService {

	@Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PayrollPolicyRepository payrollPolicyRepo;
    
    @Autowired
    private ComplianceReportRepository complianceReportRepo;
    
    @Autowired
    private DeductionsRepository deductionsRepo;
    
    @Autowired
    private BenefitsRepository benefitsRepo;
    
    @Autowired
    private RoleRepository  roleRepo;

    
    
    // Employee Management
    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
    	
    	
    	   User user = userRepo.findById(employeeDto.getUserId())
    	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + employeeDto.getUserId()));
    	    
    	   Deductions deductions = deductionsRepo.findById(employeeDto.getDeductionId())
    	            .orElseThrow(() -> new RuntimeException("Deductions not found with ID: " + employeeDto.getDeductionId()));
    	    
    	   Benefits benefits = benefitsRepo.findById(employeeDto.getBenefitId())
    	            .orElseThrow(() -> new RuntimeException("Benefits not found with ID: " + employeeDto.getBenefitId()));
    	    
    	   Role role = roleRepo.findById(employeeDto.getRoleId())
    	            .orElseThrow(() -> new RuntimeException("Role not found with ID: " + employeeDto.getRoleId()));

    	
        Employee employee = new Employee();
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setPosition(employeeDto.getPosition());
        employee.setEmployeeDepartment(employeeDto.getEmployeeDepartment());
        employee.setEmployeeSalary(employeeDto.getEmployeesalary());
        employee.setJoinDate(employeeDto.getJoinDate());
        employee.setUser(user);
        employee.setDeductions(deductions);
        employee.setBenefits(benefits);
        employee.setRole(role);
        
        if (employeeDto.getManagerId() != null) {
            Employee manager = employeeRepo.findById(employeeDto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            employee.setManager(manager);
        }


    	
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(int empId ,EmployeeDto employeeDto) {
    	
        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id: "+empId+" not found"));
        // Fetch the existing Employee
        Employee existingEmployee = employeeRepo.findById(employeeDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeDto.getUserId()));

        // Fetch related entities if provided in the DTO
        if (employeeDto.getDeductionId() != 0) {
            Deductions deductions = deductionsRepo.findById(employeeDto.getDeductionId())
                    .orElseThrow(() -> new RuntimeException("Deductions not found with ID: " + employeeDto.getDeductionId()));
            existingEmployee.setDeductions(deductions);
        }

        if (employeeDto.getBenefitId() != 0) {
            Benefits benefits = benefitsRepo.findById(employeeDto.getBenefitId())
                    .orElseThrow(() -> new RuntimeException("Benefits not found with ID: " + employeeDto.getBenefitId()));
            existingEmployee.setBenefits(benefits);
        }

        if (employeeDto.getRoleId() != 0) {
            Role role = roleRepo.findById(employeeDto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + employeeDto.getRoleId()));
            existingEmployee.setRole(role);
        }

        Employee manager = null;
        
        if (employeeDto.getManagerId() != null) {
            manager = employeeRepo.findById(employeeDto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with ID: " + employeeDto.getManagerId()));
            existingEmployee.setManager(manager);
        }

        // Update other fields
        existingEmployee.setEmployeeName(employeeDto.getEmployeeName());
        existingEmployee.setPosition(employeeDto.getPosition());
        existingEmployee.setEmployeeDepartment(employeeDto.getEmployeeDepartment());
        existingEmployee.setEmployeeSalary(employeeDto.getEmployeesalary());
        existingEmployee.setJoinDate(employeeDto.getJoinDate());
        existingEmployee.setManager(manager);

        // Save the updated employee
        return employeeRepo.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(int empId) {
        employeeRepo.deleteById(empId);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        return employeeRepo.findById(empId).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    
    
    // User Management
    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(int userId ,User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public User getUserById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Payroll Policy Management
    @Override
    public PayrollPolicy addPayrollPolicy(PayrollPolicy policy) {
        return payrollPolicyRepo.save(policy);
    }

    @Override
    public PayrollPolicy updatePayrollPolicy(int policyId,PayrollPolicy policy) {
        return payrollPolicyRepo.save(policy);
    }

    @Override
    public void deletePayrollPolicy(int policyId) {
        payrollPolicyRepo.deleteById(policyId);
    }

    @Override
    public PayrollPolicy getPayrollPolicyById(int policyId) {
        return payrollPolicyRepo.findById(policyId).orElse(null);
    }

    @Override
    public List<PayrollPolicy> getAllPayrollPolicies() {
        return payrollPolicyRepo.findAll();
    }



    // Compliance Reporting
    @Override
    public ComplianceReport addComplianceReport(ComplianceReport report) {
        return complianceReportRepo.save(report);
    }

    @Override
    public ComplianceReport updateComplianceReport(int reportId,ComplianceReport report) {
        return complianceReportRepo.save(report);
    }

    @Override
    public void deleteComplianceReport(int reportId) {
        complianceReportRepo.deleteById(reportId);
    }

    @Override
    public ComplianceReport getComplianceReportById(int reportId) {
        return complianceReportRepo.findById(reportId).orElse(null);
    }

    @Override
    public List<ComplianceReport> getAllComplianceReports() {
        return complianceReportRepo.findAll();
    }
}
