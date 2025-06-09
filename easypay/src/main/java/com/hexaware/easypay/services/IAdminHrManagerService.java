package com.hexaware.easypay.services;

import java.util.List;

import com.hexaware.easypay.dto.ComplianceReportDTO;
import com.hexaware.easypay.dto.ComplianceReportReceiveDTO;
import com.hexaware.easypay.dto.EmployeeDto;
import com.hexaware.easypay.dto.EmployeeReceiveDTO;
import com.hexaware.easypay.entities.ComplianceReport;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.PayrollPolicy;
import com.hexaware.easypay.entities.User;
import com.hexaware.easypay.exceptions.ComplianceReportNotFoundException;
import com.hexaware.easypay.exceptions.EmployeeNotFoundException;
import com.hexaware.easypay.exceptions.PayrollPolicyNotFoundException;
import com.hexaware.easypay.exceptions.UserNotFoundException;

public interface IAdminHrManagerService {
	  // Employee Management
    Employee addEmployee(EmployeeDto employee);
    Employee updateEmployee(int empId,EmployeeReceiveDTO employeeDto);
    void deleteEmployee(int empId) throws EmployeeNotFoundException;
    EmployeeReceiveDTO getEmployeeById(int empId) throws EmployeeNotFoundException;
    List<EmployeeReceiveDTO> getAllEmployees()throws EmployeeNotFoundException;

    // User Management
    User addUser(User user);
    User updateUser(int userId,User user)throws UserNotFoundException;;
    void deleteUser(int userId) throws UserNotFoundException;
    User getUserById(int userId) throws UserNotFoundException;
    List<User> getAllUsers()throws UserNotFoundException;

    // Payroll Policy Management
    PayrollPolicy addPayrollPolicy(PayrollPolicy policy);
    PayrollPolicy updatePayrollPolicy(int policyId,PayrollPolicy policy)throws PayrollPolicyNotFoundException;
    void deletePayrollPolicy(int policyId) throws PayrollPolicyNotFoundException;
    PayrollPolicy getPayrollPolicyById(int policyId) throws PayrollPolicyNotFoundException;
    List<PayrollPolicy> getAllPayrollPolicies()throws PayrollPolicyNotFoundException;

    // Compliance Reporting
    ComplianceReport addComplianceReport(ComplianceReportDTO report);
    ComplianceReport updateComplianceReport(int reportId,ComplianceReportReceiveDTO report);
    void deleteComplianceReport(int reportId) throws ComplianceReportNotFoundException;
    ComplianceReportReceiveDTO getComplianceReportById(int reportId) throws ComplianceReportNotFoundException;
    List<ComplianceReport> getAllComplianceReports();
}