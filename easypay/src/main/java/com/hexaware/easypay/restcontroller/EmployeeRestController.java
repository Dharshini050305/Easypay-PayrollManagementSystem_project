package com.hexaware.easypay.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.hexaware.easypay.dto.AttendanceDto;
import com.hexaware.easypay.dto.EmpDto;
import com.hexaware.easypay.dto.LeavesDto;
import com.hexaware.easypay.entities.Attendance;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;
import com.hexaware.easypay.services.IEmployeeService;

public class EmployeeRestController {
	@Autowired
	IEmployeeService service;
	
	Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
	
	//View PayStubs
	
	@GetMapping("/paystubs/{employeeid}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity < List<Payroll>> getPayStubs(@PathVariable int employeeid){
		
		
		List<Payroll> paystubs= service.getPayStubs(employeeid);
		logger.info(" Paystubs for Employee: "+employeeid);
		return new ResponseEntity<List<Payroll>>(paystubs,HttpStatus.ACCEPTED);
		
	}
	
	
	//Update Personal Information
	
	@PutMapping("/updatepersonalinfo/{employeeId}")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public Employee updatePersonalInformation( @PathVariable int employeeId,  @RequestBody EmpDto updatedInfo){
		
		logger.info("Updated Personal Information");
		return service.updatePersonalInformation(employeeId, updatedInfo);
		
	}
	
	//Submit Attendance
	
	@PostMapping("/submitattendance/{employeeId}")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<Attendance> submitAttendance(@PathVariable int employeeId, @RequestBody AttendanceDto attendance){
		
		Attendance savedAttendance= service.submitAttendance(employeeId, attendance);
		ResponseEntity<Attendance>responseEntity = new ResponseEntity<Attendance>(savedAttendance,HttpStatus.CREATED);
		logger.info("Attendance Submission Succesful");
		return responseEntity;
		
	}
	
	
	//RequestForLeaves
	
	@PostMapping("/requestleave/{employeeId}")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<Leaves> requestLeave(@PathVariable int employeeId, @RequestBody LeavesDto leavesDto) {
        // Create a new leave request
        Leaves leavesrequest = service.requestLeave(employeeId, leavesDto);
        ResponseEntity<Leaves>responseEntity = new ResponseEntity<Leaves>(leavesrequest,HttpStatus.CREATED);
		logger.info("Leave Request Submission Succesful");
		return responseEntity;
        
    }
	
}
