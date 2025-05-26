package com.hexaware.easypay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.easypay.dto.EmployeeDTO;
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
    public Employee addEmployee(EmployeeDTO employeeDTO) {
    	
    	
    	   User user = userRepo.findById(employeeDTO.getUserId())
    	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + employeeDTO.getUserId()));
    	    
    	   Deductions deductions = deductionsRepo.findById(employeeDTO.getDeductionId())
    	            .orElseThrow(() -> new RuntimeException("Deductions not found with ID: " + employeeDTO.getDeductionId()));
    	    
    	   Benefits benefits = benefitsRepo.findById(employeeDTO.getBenefitId())
    	            .orElseThrow(() -> new RuntimeException("Benefits not found with ID: " + employeeDTO.getBenefitId()));
    	    
    	   Role role = roleRepo.findById(employeeDTO.getRoleId())
    	            .orElseThrow(() -> new RuntimeException("Role not found with ID: " + employeeDTO.getRoleId()));

    	
        Employee employee = new Employee();
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setEmployeeDepartment(employeeDTO.getEmployeeDepartment());
        employee.setEmployeeSalary(employeeDTO.getEmployeesalary());
        employee.setJoinDate(employeeDTO.getJoinDate());
        employee.setUser(user);
        employee.setDeductions(deductions);
        employee.setBenefits(benefits);
        employee.setRole(role);
        
        if (employeeDTO.getManagerId() != null) {
            Employee manager = employeeRepo.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            employee.setManager(manager);
        }


    	
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(int empId ,EmployeeDTO employeeDTO) {
    	
        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id: "+empId+" not found"));
        // Fetch the existing Employee
        Employee existingEmployee = employeeRepo.findById(employeeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeDTO.getUserId()));

        // Fetch related entities if provided in the DTO
        if (employeeDTO.getDeductionId() != 0) {
            Deductions deductions = deductionsRepo.findById(employeeDTO.getDeductionId())
                    .orElseThrow(() -> new RuntimeException("Deductions not found with ID: " + employeeDTO.getDeductionId()));
            existingEmployee.setDeductions(deductions);
        }

        if (employeeDTO.getBenefitId() != 0) {
            Benefits benefits = benefitsRepo.findById(employeeDTO.getBenefitId())
                    .orElseThrow(() -> new RuntimeException("Benefits not found with ID: " + employeeDTO.getBenefitId()));
            existingEmployee.setBenefits(benefits);
        }

        if (employeeDTO.getRoleId() != 0) {
            Role role = roleRepo.findById(employeeDTO.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found with ID: " + employeeDTO.getRoleId()));
            existingEmployee.setRole(role);
        }

        Employee manager = null;
        
        if (employeeDTO.getManagerId() != null) {
            manager = employeeRepo.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with ID: " + employeeDTO.getManagerId()));
            existingEmployee.setManager(manager);
        }

        // Update other fields
        existingEmployee.setEmployeeName(employeeDTO.getEmployeeName());
        existingEmployee.setPosition(employeeDTO.getPosition());
        existingEmployee.setEmployeeDepartment(employeeDTO.getEmployeeDepartment());
        existingEmployee.setEmployeeSalary(employeeDTO.getEmployeesalary());
        existingEmployee.setJoinDate(employeeDTO.getJoinDate());
        existingEmployee.setManager(manager);

        // Save the updated employee
        return employeeRepo.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepo.deleteById(employeeId);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeRepo.findById(employeeId).orElse(null);
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
