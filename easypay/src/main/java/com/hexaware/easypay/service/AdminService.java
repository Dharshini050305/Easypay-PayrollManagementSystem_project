package com.hexaware.easypay.service;

import java.util.List;


import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.exception.EmployeeNotFoundException;
import com.hexaware.easypay.exception.UserNotFoundException;
import com.hexaware.easypay.exception.PayrollNotFoundException;

public interface AdminService {
	// Employee Management
    Employee addEmployee(EmployeeDTO employee);
    Employee updateEmployee(int employeeId, Employee employee);
    void deleteEmployee(int employeeId) throws EmployeeNotFoundException;
    EmployeeDTO getEmployeeById(int employeeId) throws EmployeeNotFoundException;
    List<EmployeeDTO> getAllEmployees() throws EmployeeNotFoundException;

    // User Management
    User addUser(User user);
    User updateUser(int userId,User user)throws UserNotFoundException;;
    void deleteUser(int userId) throws UserNotFoundException;
    User getUserById(int userId) throws UserNotFoundException;
    List<User> getAllUsers()throws UserNotFoundException;;

    // Payroll  Management
    Payroll addPayroll(PayrollDTO dto);
    Payroll updatePayroll(int payrollId, PayrollDTO dto);
    void deletePayroll(int payrollId) throws PayrollNotFoundException;
    PayrollDTO getPayrollById(int payrollId) throws PayrollNotFoundException;
    List<PayrollDTO> getAllPayroll()throws PayrollNotFoundException;

   

}