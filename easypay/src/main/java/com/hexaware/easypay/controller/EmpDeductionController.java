package com.hexaware.easypay.controller;
/**
 * REST controller for employee-deductions-related operations in the Payroll Management System.
 * * 
 * @author Dharshini
 * @version 1.0
 * */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.EmployeeDeductionVO;
import com.hexaware.easypay.service.EmpDeductionServiceImpl;



@RestController
@RequestMapping("/employee")
public class EmpDeductionController {

    @Autowired
    private EmpDeductionServiceImpl deductionService;

    @GetMapping("/deduction/{employeeId}")
    public ResponseEntity<EmployeeDeductionVO> getDeductionByEmployeeId(@PathVariable int employeeId) {
        return ResponseEntity.ok(deductionService.getDeductionByEmployeeId(employeeId));
    }
}