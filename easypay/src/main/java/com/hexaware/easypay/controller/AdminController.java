package com.hexaware.easypay.controller;
/**
 * REST controller for admin-related operations in the Payroll Management System.
 * * 
 * @author Dharshini
 * @version 1.0
 * */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.exception.PayrollNotFoundException;
import com.hexaware.easypay.service.AdminServiceImpl;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminServiceImpl service;

    // Employee Management
    @PostMapping("/employee/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDto) {
        logger.info("Adding a new employee: {}", employeeDto);
        Employee addedEmployee = service.addEmployee(employeeDto);
        logger.info("Employee added successfully: {}", addedEmployee);
        return addedEmployee;
    }

    @PutMapping("/employee/update/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee employeeDto) {
        logger.info("Updating employee with ID: {}", employeeId);
        Employee updatedEmployee = service.updateEmployee(employeeId, employeeDto);
        logger.info("Employee updated successfully: {}", updatedEmployee);
        return updatedEmployee;
    }

    @DeleteMapping("/employee/delete/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable int employeeId) {
        logger.info("Deleting employee with ID: {}", employeeId);
        service.deleteEmployee(employeeId);
        logger.info("Employee with ID {} deleted successfully.", employeeId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee with ID " + employeeId + " deleted successfully.");

        return ResponseEntity.ok(response); 
    }


    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EmployeeDTO getEmployeeById(@PathVariable int employeeId) {
        logger.info("Fetching employee with ID: {}", employeeId);
        EmployeeDTO employee = service.getEmployeeById(employeeId);
        logger.info("Employee fetched successfully: {}", employee);
        return employee;
    }
    @GetMapping("/employees")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<EmployeeDTO> getAllEmployees() {
        logger.info("Fetching all employees.");
        List<EmployeeDTO> employees = service.getAllEmployees();  
        logger.info("Total employees fetched: {}", employees.size());
        return employees;
    }

    // User Management
    @PostMapping("/user/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User addUser(@RequestBody User user) {
        logger.info("Adding a new user: {}", user);
        User addedUser = service.addUser(user);
        logger.info("User added successfully: {}", addedUser);
        return addedUser;
    }

    @PutMapping("/user/update/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        logger.info("Updating user with ID: {}", userId);
        User updatedUser = service.updateUser(userId, user);
        logger.info("User updated successfully: {}", updatedUser);
        return updatedUser;
    }

    @DeleteMapping("/user/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable int userId) {
        logger.info("Deleting user with ID: {}", userId);
        service.deleteUser(userId);
        logger.info("User with ID {} deleted successfully.", userId);
        return "User with ID " + userId + " deleted successfully.";
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUserById(@PathVariable int userId) {
        logger.info("Fetching user with ID: {}", userId);
        User user = service.getUserById(userId);
        logger.info("User fetched successfully: {}", user);
        return user;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        logger.info("Fetching all users.");
        List<User> users = service.getAllUsers();
        logger.info("Total users fetched: {}", users.size());
        return users;
    }

 // Payroll  Management
    @PostMapping("/payroll/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public PayrollDTO addPayroll(@RequestBody PayrollDTO payrollDto) {
        logger.info("Adding a new payroll : {}", payrollDto);
        Payroll addedPayroll = service.addPayroll(payrollDto);
        
        PayrollDTO addedDto = new PayrollDTO(
            addedPayroll.getPayrollId(),
            addedPayroll.getBenefits(),
            addedPayroll.getDeductions(),
            addedPayroll.getGrossPay(),
            addedPayroll.getNetPay(),
            addedPayroll.getPayrollDate(),
            addedPayroll.getEmployee().getEmployeeId()
        );

        logger.info("Payroll added successfully: {}", addedDto);
        return addedDto;
    }

    @PutMapping("/payroll/update/{payrollId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public PayrollDTO updatePayroll(@PathVariable int payrollId, @RequestBody PayrollDTO payrollDto) {
        logger.info("Updating payroll with ID: {}", payrollId);
        Payroll updatedPayroll = service.updatePayroll(payrollId, payrollDto);

 
        PayrollDTO updatedDto = new PayrollDTO(
            updatedPayroll.getPayrollId(),
            updatedPayroll.getBenefits(),
            updatedPayroll.getDeductions(),
            updatedPayroll.getGrossPay(),
            updatedPayroll.getNetPay(),
            updatedPayroll.getPayrollDate(),
            updatedPayroll.getEmployee().getEmployeeId()
        );

        logger.info("Payroll updated successfully: {}", updatedDto);
        return updatedDto;
    }



    @DeleteMapping("/payroll/delete/{payrollId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePayroll(@PathVariable int payrollId) {
        logger.info("Deleting payroll  with ID: {}", payrollId);
        service.deletePayroll(payrollId);
        logger.info("Payroll with ID {} deleted successfully.", payrollId);
        return "Payroll with ID " + payrollId + " deleted successfully.";
    }

    @GetMapping("/payroll/{payrollId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PayrollDTO> getPayrollById(@PathVariable int payrollId) {
        logger.info("Fetching payroll with ID: {}", payrollId);
        try {
            PayrollDTO payrollDTO = service.getPayrollById(payrollId);
            logger.info("Payroll fetched successfully: {}", payrollDTO);
            return ResponseEntity.ok(payrollDTO);
        } catch (PayrollNotFoundException e) {
            logger.error("Payroll not found for ID: {}", payrollId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payroll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<PayrollDTO> getAllPayroll() {
        logger.info("Fetching all payrolls.");
        List<PayrollDTO> payroll = service.getAllPayroll();
        logger.info("Total payroll fetched: {}", payroll.size());
        return payroll;
    }

   
}
