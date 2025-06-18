package com.hexaware.easypay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.EmployeeDeductionVO;
import com.hexaware.easypay.service.DeductionServiceImpl;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/deduction")
public class DeductionController {
	@Autowired
	DeductionServiceImpl service;
	
	
	@GetMapping("/get/employee-role/{employeeId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public EmployeeDeductionVO getEmployeeAndDeductionById(@PathVariable int  employeeId) {
		
		return service.getEmployeeAndDeductionById(employeeId);
		
	}
}
