package com.hexaware.easypay.services;

import com.hexaware.easypay.entity.Employee;

public class IEmployeeService {
	Employee addEmployee(Employee employee);
    Employee updateEmployee(Long id, EmployeeDTO employeeDTO) throws ResourceNotFoundException;
    Employee getEmployeeById(Long id) throws ResourceNotFoundException;
    List<Employee> getAllEmployees();
    List<Employee> getActiveEmployees();
    void deleteEmployee(Long id) throws ResourceNotFoundException;
    List<Employee> getEmployeesByManager(Long managerId);
    
 // New method for employee verification
    boolean verifyEmployeeDetails(String employeeCode, String email);
    
    Optional<Employee> findByEmail(String email);
}
