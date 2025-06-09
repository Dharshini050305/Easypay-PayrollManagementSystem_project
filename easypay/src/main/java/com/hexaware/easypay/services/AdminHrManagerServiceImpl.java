package com.hexaware.easypay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hexaware.easypay.dto.ComplianceReportDTO;
import com.hexaware.easypay.dto.ComplianceReportReceiveDTO;
import com.hexaware.easypay.dto.EmployeeDto;
import com.hexaware.easypay.dto.EmployeeReceiveDTO;
import com.hexaware.easypay.entities.Benefits;
import com.hexaware.easypay.entities.ComplianceReport;
import com.hexaware.easypay.entities.Deductions;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.PayrollPolicy;
import com.hexaware.easypay.entities.Role;
import com.hexaware.easypay.entities.User;
import com.hexaware.easypay.exceptions.ComplianceReportNotFoundException;
import com.hexaware.easypay.exceptions.EmployeeNotFoundException;
import com.hexaware.easypay.exceptions.PayrollPolicyNotFoundException;
import com.hexaware.easypay.repositories.BenefitsRepository;
import com.hexaware.easypay.repositories.ComplianceReportRepository;
import com.hexaware.easypay.repositories.DeductionsRepository;
import com.hexaware.easypay.repositories.EmployeeRepository;
import com.hexaware.easypay.repositories.PayrollPolicyRepository;
import com.hexaware.easypay.repositories.RoleRepository;
import com.hexaware.easypay.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
    	try {
    	
    	   User user = userRepo.findById(employeeDto.getUserId())
    	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + employeeDto.getUserId()));
    	    
    	   Deductions deductions = deductionsRepo.findById(employeeDto.getDeductionId())
    	            .orElseThrow(() -> new RuntimeException("Deductions not found with ID: " + employeeDto.getDeductionId()));
    	    
    	   Benefits benefits = benefitsRepo.findById(employeeDto.getBenefitId())
    	            .orElseThrow(() -> new RuntimeException("Benefits not found with ID: " + employeeDto.getBenefitId()));
    	    
    	   Role role = roleRepo.findById(employeeDto.getRoleId())
    	            .orElseThrow(() -> new RuntimeException("Role not found with ID: " + employeeDto.getRoleId()));

    	
        Employee employee = new Employee();
        employee.setEmpName(employeeDto.getEmpName());
        employee.setPosition(employeeDto.getPosition());
        employee.setEmpDepartment(employeeDto.getEmpDepartment());
        employee.setEmpsalary(employeeDto.getEmpsalary());
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
    	}catch (RuntimeException e) {
            // Log the exception and return a response with the error message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    
    
    
    @Override
    public Employee updateEmployee(int empId ,EmployeeReceiveDTO employeeDto) {
    	

        // Fetch the existing Employee
        Employee existingEmployee = employeeRepo.findById(employeeDto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeDto.getEmpId()));

        
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

        
        existingEmployee.setEmpName(employeeDto.getEmpName());
        existingEmployee.setPosition(employeeDto.getPosition());
        existingEmployee.setEmpDepartment(employeeDto.getEmpDepartment());
        existingEmployee.setEmpsalary(employeeDto.getEmpsalary());
        existingEmployee.setJoinDate(employeeDto.getJoinDate());
        existingEmployee.setManager(manager);

       
        return employeeRepo.save(existingEmployee);
    }

  
    @Transactional
    @Override
    public void deleteEmployee(int empId) {
        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employeeRepo.delete(employee);
    }

    @Override
    public EmployeeReceiveDTO getEmployeeById(int empId) {
        // Fetch the Employee entity by ID
        Employee employee = employeeRepo.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + empId + " not found."));

        // Map Employee entity to EmployeeReceiveDTO
        Integer managerId = (employee.getManager() != null) ? employee.getManager().getEmpId() : null;

        return new EmployeeReceiveDTO(
                employee.getEmpId(),                       
                employee.getEmpName(),                 
                employee.getPosition(),                     
                employee.getEmpDepartment(),              
                employee.getEmpsalary(),                   
                employee.getJoinDate(),                    
                employee.getUser().getUserId(),             
                employee.getDeductions().getDeductionId(),  
                employee.getBenefits().getBenifitId(),      
                employee.getRole().getRoleID(),             
                managerId                                   
        );
    }



    @Override
    public List<EmployeeReceiveDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll(); // Fetch all Employee entities

        // Map Employee entities to EmployeeReceiveDTO
        List<EmployeeReceiveDTO> employeeDtos = employees.stream()
            .map(employee -> {
                Integer managerId = (employee.getManager() != null) ? employee.getManager().getEmpId() : null;
                
                // Use the constructor to create a new DTO object
                return new EmployeeReceiveDTO(
                    employee.getEmpId(),                         // Set empId
                    employee.getEmpName(),                      // Set empName
                    employee.getPosition(),                     // Set position
                    employee.getEmpDepartment(),                // Set empDepartment
                    employee.getEmpsalary(),                    // Set empsalary
                    employee.getJoinDate(),                     // Set joinDate
                    employee.getUser().getUserId(),             // Set userId
                    employee.getDeductions().getDeductionId(),  // Set deductionId
                    employee.getBenefits().getBenifitId(),      // Set benefitId
                    employee.getRole().getRoleID(),             // Set roleId
                    managerId                                   // Set managerId
                );
            })
            .toList();

        return employeeDtos;
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

    @Override
    public PayrollPolicy addPayrollPolicy(PayrollPolicy policy) {
        return payrollPolicyRepo.save(policy);
    }

    @Override
    public PayrollPolicy updatePayrollPolicy(int policyId, PayrollPolicy policy) {
      
        if (!payrollPolicyRepo.existsById(policyId)) {
            throw new PayrollPolicyNotFoundException("Payroll Policy with ID " + policyId + " not found.");
        }
        return payrollPolicyRepo.save(policy);
    }

    @Override
    public void deletePayrollPolicy(int policyId) {
        
        if (!payrollPolicyRepo.existsById(policyId)) {
            throw new PayrollPolicyNotFoundException("Payroll Policy with ID " + policyId + " not found.");
        }
        payrollPolicyRepo.deleteById(policyId);
    }

    @Override
    public PayrollPolicy getPayrollPolicyById(int policyId) {
        return payrollPolicyRepo.findById(policyId)
            .orElseThrow(() -> new PayrollPolicyNotFoundException("Payroll Policy with ID " + policyId + " not found."));
    }

    @Override
    public List<PayrollPolicy> getAllPayrollPolicies() {
        return payrollPolicyRepo.findAll();
    }



    //----------------------------------------- Compliance Reporting-------------------------------------------
    
    @Override
    public ComplianceReport addComplianceReport(ComplianceReportDTO report) {


        // Fetch the employee associated with the compliance report
        Employee employee = employeeRepo.findById(report.getEmp_id())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + report.getEmp_id()));

        // Map DTO to ComplianceReport entity
        ComplianceReport complianceReport = new ComplianceReport();
        complianceReport.setReportDate(report.getReportDate());
        complianceReport.setComplianceStatus(report.getComplianceStatus());
        complianceReport.setEmployee(employee);

        // Save the compliance report
        return complianceReportRepo.save(complianceReport);
    }


    @Override
    public ComplianceReport updateComplianceReport(int reportId, ComplianceReportReceiveDTO reportDTO) {

        // Check if the compliance report exists
        ComplianceReport existingReport = complianceReportRepo.findById(reportId)
                .orElseThrow(() -> new ComplianceReportNotFoundException(
                        "Compliance Report with ID " + reportId + " not found."));

        // Fetch the employee associated with the compliance report
        Employee employee = employeeRepo.findById(reportDTO.getEmp_id())
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + reportDTO.getEmp_id()));

        // Update the fields of the existing report
        existingReport.setReportId(reportDTO.getReportId());
        existingReport.setReportDate(reportDTO.getReportDate());
        existingReport.setComplianceStatus(reportDTO.getComplianceStatus());
        existingReport.setEmployee(employee);

        // Save and return the updated compliance report
        return complianceReportRepo.save(existingReport);
    }


    @Override
    public void deleteComplianceReport(int reportId) {
       
        if (!complianceReportRepo.existsById(reportId)) {
            throw new ComplianceReportNotFoundException("Compliance Report with ID " + reportId + " not found.");
        }
        complianceReportRepo.deleteById(reportId);
    }

    @Override
    public ComplianceReportReceiveDTO getComplianceReportById(int reportId) {
        ComplianceReport complianceReport = complianceReportRepo.findById(reportId)
                .orElseThrow(() -> new ComplianceReportNotFoundException(
                        "Compliance Report with ID " + reportId + " not found."));

        // Map the entity to the DTO
        return new ComplianceReportReceiveDTO(
                complianceReport.getReportId(),
                complianceReport.getReportDate(),
                complianceReport.getComplianceStatus(),
                complianceReport.getEmployee().getEmpId() 
        );
    }


    @Override
    public List<ComplianceReport> getAllComplianceReports() {
        return complianceReportRepo.findAll();
    }

}