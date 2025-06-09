package com.hexaware.easypay.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.hexaware.easypay.dto.ComplianceReportDTO;
import com.hexaware.easypay.dto.ComplianceReportReceiveDTO;
import com.hexaware.easypay.dto.EmployeeDto;
import com.hexaware.easypay.dto.EmployeeReceiveDTO;
import com.hexaware.easypay.entities.ComplianceReport;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.PayrollPolicy;
import com.hexaware.easypay.entities.User;
import com.hexaware.easypay.services.IAdminHrManagerService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/adminhr")
public class AdminHrManagerRestController {
	 Logger logger = LoggerFactory.getLogger(AdminHrManagerRestController.class);

	    @Autowired
	    private IAdminHrManagerService service;

	    // Employee Management
	    @PostMapping("/employee/add")
	    @PreAuthorize("hasAuthority('HR')")
	    public Employee addEmployee(@RequestBody EmployeeDto employeeDto) {
	        logger.info("Adding a new employee: {}", employeeDto);
	        Employee addedEmployee = service.addEmployee(employeeDto);
	        logger.info("Employee added successfully: {}", addedEmployee);
	        return addedEmployee;
	    }

	    @PutMapping("/employee/update/{empId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public Employee updateEmployee( @PathVariable int empId,@RequestBody EmployeeReceiveDTO employeeDto) {
	        logger.info("Updating employee : {}", employeeDto);
	        Employee updatedEmployee = service.updateEmployee(empId,employeeDto);
	        logger.info("Employee updated successfully: {}", updatedEmployee);
	        return updatedEmployee;
	    }

	    @DeleteMapping("/employee/delete/{empId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public String deleteEmployee(@PathVariable int empId) {
	        logger.info("Deleting employee with ID: {}", empId);
	        service.deleteEmployee(empId);
	        logger.info("Employee with ID {} deleted successfully.", empId);
	        return "Employee with ID " + empId + " deleted successfully.";
	    }

	    @GetMapping("/employee/{empId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public EmployeeReceiveDTO getEmployeeById(@PathVariable int empId) {
	        logger.info("Fetching employee with ID: {}", empId);
	        EmployeeReceiveDTO employee = service.getEmployeeById(empId);
	        logger.info("Employee fetched successfully: {}", employee);
	        return employee;
	    }

	    @GetMapping("/employees")
	    @PreAuthorize("hasAuthority('HR')")
	    public List<EmployeeReceiveDTO> getAllEmployees() {
	        logger.info("Fetching all employees.");
	        List<EmployeeReceiveDTO> employeeDto = service.getAllEmployees();
	        logger.info("Total employees fetched: {}", employeeDto.size());
	        return employeeDto;
	    }

	    // User Management
	    @PostMapping("/user/add")
	    @PreAuthorize("hasAuthority('HR')")
	    public User addUser(@RequestBody User user) {
	        logger.info("Adding a new user: {}", user);
	        User addedUser = service.addUser(user);
	        logger.info("User added successfully: {}", addedUser);
	        return addedUser;
	    }

	    @PutMapping("/user/update/{userId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public User updateUser(@PathVariable int uerId,@RequestBody User user) {
	        logger.info("Updating user with ID: {}", user.getUserId());
	        User updatedUser = service.updateUser( uerId,user);
	        logger.info("User updated successfully: {}", updatedUser);
	        return updatedUser;
	    }

	    @DeleteMapping("/user/delete/{userId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public String deleteUser(@PathVariable int userId) {
	        logger.info("Deleting user with ID: {}", userId);
	        service.deleteUser(userId);
	        logger.info("User with ID {} deleted successfully.", userId);
	        return "User with ID " + userId + " deleted successfully.";
	    }

	    @GetMapping("/user/{userId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public User getUserById(@PathVariable int userId) {
	        logger.info("Fetching user with ID: {}", userId);
	        User user = service.getUserById(userId);
	        logger.info("User fetched successfully: {}", user);
	        return user;
	    }

	    @GetMapping("/users")
	    @PreAuthorize("hasAuthority('HR')")
	    public List<User> getAllUsers() {
	        logger.info("Fetching all users.");
	        List<User> users = service.getAllUsers();
	        logger.info("Total users fetched: {}", users.size());
	        return users;
	    }

	    // -----------------------------------Payroll Policy Management------------------------------------------
	    
	    @PostMapping("/payrollPolicy/add")
	    @PreAuthorize("hasAuthority('HR')")
	    public PayrollPolicy addPayrollPolicy(@RequestBody PayrollPolicy policy) {
	        logger.info("Adding a new payroll policy: {}", policy);
	        PayrollPolicy addedPolicy = service.addPayrollPolicy(policy);
	        logger.info("Payroll policy added successfully: {}", addedPolicy);
	        return addedPolicy;
	    }

	    @PutMapping("/payrollPolicy/update/{policyId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public PayrollPolicy updatePayrollPolicy(@PathVariable int policyId,@RequestBody PayrollPolicy policy) {
	        logger.info("Updating payroll policy with ID: {}", policy.getPolicyId());
	        PayrollPolicy updatedPolicy = service.updatePayrollPolicy(policyId,policy);
	        logger.info("Payroll policy updated successfully: {}", updatedPolicy);
	        return updatedPolicy;
	    }

	    @DeleteMapping("/payrollPolicy/delete/{policyId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public String deletePayrollPolicy(@PathVariable int policyId) {
	        logger.info("Deleting payroll policy with ID: {}", policyId);
	        service.deletePayrollPolicy(policyId);
	        logger.info("Payroll policy with ID {} deleted successfully.", policyId);
	        return "Payroll policy with ID " + policyId + " deleted successfully.";
	    }

	    @GetMapping("/payrollPolicy/{policyId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public PayrollPolicy getPayrollPolicyById(@PathVariable int policyId) {
	        logger.info("Fetching payroll policy with ID: {}", policyId);
	        PayrollPolicy policy = service.getPayrollPolicyById(policyId);
	        logger.info("Payroll policy fetched successfully: {}", policy);
	        return policy;
	    }

	    @GetMapping("/payrollPolicies")
	    @PreAuthorize("hasAuthority('HR')")
	    public List<PayrollPolicy> getAllPayrollPolicies() {
	        logger.info("Fetching all payroll policies.");
	        List<PayrollPolicy> policies = service.getAllPayrollPolicies();
	        logger.info("Total payroll policies fetched: {}", policies.size());
	        return policies;
	    }

	    // ---------------------------------------Compliance Reporting-------------------------------
	    
	    @PostMapping("/complianceReport/add")
	    @PreAuthorize("hasAuthority('HR')")
	    public ComplianceReport addComplianceReport(@RequestBody ComplianceReportDTO report) {
	        logger.info("Adding a new compliance report: {}", report);
	        ComplianceReport addedReport = service.addComplianceReport(report);
	        logger.info("Compliance report added successfully: {}", addedReport);
	        return addedReport;
	    }

	    @PutMapping("/complianceReport/update{reportId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public ComplianceReport updateComplianceReport(@PathVariable int reportId,@RequestBody ComplianceReportReceiveDTO report) {
	        logger.info("Updating compliance report with ID: {}", report.getReportId());
	        ComplianceReport updatedReport = service.updateComplianceReport(reportId,report);
	        logger.info("Compliance report updated successfully: {}", updatedReport);
	        return updatedReport;
	    }

	    @DeleteMapping("/complianceReport/delete/{reportId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public String deleteComplianceReport(@PathVariable int reportId) {
	        logger.info("Deleting compliance report with ID: {}", reportId);
	        service.deleteComplianceReport(reportId);
	        logger.info("Compliance report with ID {} deleted successfully.", reportId);
	        return "Compliance report with ID " + reportId + " deleted successfully.";
	    }

	    @GetMapping("/complianceReport/{reportId}")
	    @PreAuthorize("hasAuthority('HR')")
	    public ComplianceReportReceiveDTO getComplianceReportById(@PathVariable int reportId) {
	        logger.info("Fetching compliance report with ID: {}", reportId);
	        ComplianceReportReceiveDTO report = service.getComplianceReportById(reportId);
	        logger.info("Compliance report fetched successfully: {}", report);
	        return report;
	    }

	    @GetMapping("/complianceReports")
	    @PreAuthorize("hasAuthority('HR')")
	    public List<ComplianceReport> getAllComplianceReports() {
	        logger.info("Fetching all compliance reports.");
	        List<ComplianceReport> reports = service.getAllComplianceReports();
	        logger.info("Total compliance reports fetched: {}", reports.size());
	        return reports;
	    }
	}