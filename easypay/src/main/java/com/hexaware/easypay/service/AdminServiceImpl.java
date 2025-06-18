package com.hexaware.easypay.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.*;
import com.hexaware.easypay.exception.EmployeeNotFoundException;
import com.hexaware.easypay.exception.PayrollNotFoundException;
import com.hexaware.easypay.repository.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PayrollRepo payrollRepo;

    @Autowired
    private DeductionsRepo deductionsRepo;

    @Autowired
    private  BenefitsRepo benefitsRepo;

    

    // Employee Management 

    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        User user = userRepo.findById(employeeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + employeeDTO.getUserId()));

        Deductions deduction = deductionsRepo.findById(employeeDTO.getDeductionId())
                .orElseThrow(() -> new RuntimeException("Deductions not found with ID: " + employeeDTO.getDeductionId()));

        Benefits benefit = benefitsRepo.findById(employeeDTO.getBenefitId())
                .orElseThrow(() -> new RuntimeException("Benefits not found with ID: " + employeeDTO.getBenefitId()));

        

        Employee employee = new Employee();
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setEmail(employeeDTO.getEmail());
        employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
        employee.setJoinDate(employeeDTO.getJoinDate());
        employee.setUser(user);
        employee.setDeduction(deduction);
        employee.setBenefit(benefit);
 
        if (employeeDTO.getManagerId() != 0) {
            Employee manager = employeeRepo.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with ID: " + employeeDTO.getManagerId()));
            employee.setManager(manager);
        }

        return employeeRepo.save(employee);
    }
   

      

    @Override
    public Employee updateEmployee(int employeeId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID: " + employeeId + " not found"));

       
        existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setEmployeeSalary(updatedEmployee.getEmployeeSalary());
        existingEmployee.setJoinDate(updatedEmployee.getJoinDate());
       existingEmployee.setEmail(updatedEmployee.getEmail());

    
        if (updatedEmployee.getManager() != null) {
            Employee manager = employeeRepo.findById(updatedEmployee.getManager().getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with ID: " + updatedEmployee.getManager().getEmployeeId()));
            existingEmployee.setManager(manager);
        }

       
        if (updatedEmployee.getDeduction() != null) {
            Deductions deduction = deductionsRepo.findById(updatedEmployee.getDeduction().getDeductionId())
                    .orElseThrow(() -> new RuntimeException("Deductions not found with ID: " + updatedEmployee.getDeduction().getDeductionId()));
            existingEmployee.setDeduction(deduction);
        }

      
        if (updatedEmployee.getBenefit() != null) {
            Benefits benefit = benefitsRepo.findById(updatedEmployee.getBenefit().getBenefitId())
                    .orElseThrow(() -> new RuntimeException("Benefits not found with ID: " + updatedEmployee.getBenefit().getBenefitId()));
            existingEmployee.setBenefit(benefit);
        }

        return employeeRepo.save(existingEmployee);
    }


    @Override
    public void deleteEmployee(int employeeId) {
        if (!employeeRepo.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
        }
        employeeRepo.deleteById(employeeId);
    }

    @Override
    public EmployeeDTO getEmployeeById(int employeeId) {
        Employee emp = employeeRepo.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + employeeId + " not found"));

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(emp.getEmployeeId());
        dto.setEmployeeName(emp.getEmployeeName());
        dto.setPosition(emp.getPosition());
        dto.setEmail(emp.getEmail());
        dto.setEmployeeSalary(emp.getEmployeeSalary());
        dto.setJoinDate(emp.getJoinDate());

        if (emp.getUser() != null) {
            dto.setUserId(emp.getUser().getUserId());
            dto.setRole(emp.getUser().getRole());
        }
        if (emp.getManager() != null) {
            dto.setManagerId(emp.getManager().getEmployeeId());
        }
        if (emp.getDeduction() != null) {
            dto.setDeductionId(emp.getDeduction().getDeductionId());
        }
        if (emp.getBenefit() != null) {
            dto.setBenefitId(emp.getBenefit().getBenefitId());
        }

        return dto;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws EmployeeNotFoundException {
        List<Employee> employees = employeeRepo.findAll();

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found");
        }

        return employees.stream()
                .map(emp -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setEmployeeId(emp.getEmployeeId());
                    dto.setEmployeeName(emp.getEmployeeName());
                    dto.setPosition(emp.getPosition());
                    dto.setEmail(emp.getEmail());
                    dto.setEmployeeSalary(emp.getEmployeeSalary());
                    dto.setJoinDate(emp.getJoinDate());

                    if (emp.getUser() != null) {
                        dto.setUserId(emp.getUser().getUserId());
                        dto.setRole(emp.getUser().getRole());
                    }
                    if (emp.getManager() != null) {
                        dto.setManagerId(emp.getManager().getEmployeeId());
                    }
                    if (emp.getDeduction() != null) {
                        dto.setDeductionId(emp.getDeduction().getDeductionId());
                    }
                    if (emp.getBenefit() != null) {
                        dto.setBenefitId(emp.getBenefit().getBenefitId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
    // User Management 

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(int userId, User user) {
        userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
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

    // Payroll Management

    @Override
    public Payroll addPayroll(PayrollDTO dto) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(dto.getEmployeeId());
        if (!optionalEmployee.isPresent()) {
            System.out.println("Employee not found with ID: " + dto.getEmployeeId());
            return null;
        }

        Payroll payroll = new Payroll();
        payroll.setBenefits(dto.getBenefits());
        payroll.setDeductions(dto.getDeductions());
        payroll.setGrossPay(dto.getGrossPay());
        payroll.setNetPay(dto.getNetPay());
        payroll.setPayrollDate(dto.getPayrollDate());
        payroll.setEmployee(optionalEmployee.get());

        return payrollRepo.save(payroll);
    }

    @Override
    public Payroll updatePayroll(int payrollId, PayrollDTO dto) {
        Optional<Payroll> optionalPayroll = payrollRepo.findById(payrollId);
        if (!optionalPayroll.isPresent()) {
            System.out.println("Payroll not found with ID: " + payrollId);
            return null;
        }

        Optional<Employee> optionalEmployee = employeeRepo.findById(dto.getEmployeeId());
        if (!optionalEmployee.isPresent()) {
            System.out.println("Employee not found with ID: " + dto.getEmployeeId());
            return null;
        }

        Payroll existing = optionalPayroll.get();
        existing.setBenefits(dto.getBenefits());
        existing.setDeductions(dto.getDeductions());
        existing.setGrossPay(dto.getGrossPay());
        existing.setNetPay(dto.getNetPay());
        existing.setPayrollDate(dto.getPayrollDate());
        existing.setEmployee(optionalEmployee.get());

        return payrollRepo.save(existing);
    }


    @Override
    public void deletePayroll(int payrollId) {
        payrollRepo.deleteById(payrollId);    
    }

    @Override
    public PayrollDTO getPayrollById(int payrollId) throws PayrollNotFoundException {
        Payroll payroll = payrollRepo.findById(payrollId)
            .orElseThrow(() -> new PayrollNotFoundException("Payroll not found for id: " + payrollId));

        PayrollDTO dto = new PayrollDTO();
        dto.setPayrollId(payroll.getPayrollId());
        dto.setBenefits(payroll.getBenefits());
        dto.setDeductions(payroll.getDeductions());
        dto.setGrossPay(payroll.getGrossPay());
        dto.setNetPay(payroll.getNetPay());
        dto.setPayrollDate(payroll.getPayrollDate());
        dto.setEmployeeId(payroll.getEmployee().getEmployeeId()); 

        return dto;
    }

    @Override
    public List<PayrollDTO> getAllPayroll() {
        List<Payroll> payrolls = payrollRepo.findAll();

        return payrolls.stream()
                .map(payroll -> {
                    PayrollDTO dto = new PayrollDTO();
                    dto.setPayrollId(payroll.getPayrollId());
                    dto.setBenefits(payroll.getBenefits());
                    dto.setDeductions(payroll.getDeductions());
                    dto.setGrossPay(payroll.getGrossPay());
                    dto.setNetPay(payroll.getNetPay());
                    dto.setPayrollDate(payroll.getPayrollDate());
                    dto.setEmployeeId(payroll.getEmployee().getEmployeeId());  
                    return dto;
                })
                .collect(Collectors.toList());
    }




	
}
