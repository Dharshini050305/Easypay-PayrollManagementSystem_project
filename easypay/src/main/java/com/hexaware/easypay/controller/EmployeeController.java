package com.hexaware.easypay.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.AttendanceDTO;
import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Attendance;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.service.EmployeeServiceImpl;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	EmployeeServiceImpl service;
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//View PayStubs
	
	@GetMapping("/paystubs/{employeeId}")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public List<PayrollDTO> getPayStubs(@PathVariable int employeeId) {
	    return service.getPayStubs(employeeId);
	}
	
	

	//Submit Attendance
	
	@PostMapping("/submitattendance/{employeeId}")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<Attendance> submitAttendance(@PathVariable int employeeId, @RequestBody AttendanceDTO attendanceDTO){
		
		Attendance savedAttendance= service.submitAttendance(employeeId, attendanceDTO);
		ResponseEntity<Attendance>responseEntity = new ResponseEntity<Attendance>(savedAttendance,HttpStatus.CREATED);
		logger.info("Attendance Submission Succesful");
		return responseEntity;
		
	}
	
	
	//RequestForLeaves
	
	@PostMapping("/requestleave/{employeeId}")
	@PreAuthorize("hasAuthority('EMPLOYEE')")
	public ResponseEntity<LeaveRequestDTO> requestLeave(@PathVariable int employeeId,
	                                                    @RequestBody LeaveRequestDTO leaveRequestDTO) {

	    LeaveRequest leaveRequest = service.requestLeave(employeeId, leaveRequestDTO);

	    // Convert Entity to DTO for response
	    LeaveRequestDTO responseDTO = new LeaveRequestDTO(
	            leaveRequest.getLeaveId(),
	            leaveRequest.getEmployee().getEmployeeId(),
	            leaveRequest.getStartDate(),
	            leaveRequest.getEndDate(),
	            leaveRequest.getLeaveType(),
	            leaveRequest.getLeaveStatus(),
	            leaveRequest.getManager() != null ? leaveRequest.getManager().getEmployeeId() : 0
	    );

	    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}

@GetMapping("/leave-status/{employeeId}")
@PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('HR')")
public ResponseEntity<List<LeaveRequestDTO>> getLeaveStatusByEmployeeId(@PathVariable int employeeId) {
    List<LeaveRequestDTO> leaveStatusList = service.getLeaveRequestsByEmployeeId(employeeId);

    if (leaveStatusList.isEmpty()) {
        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(leaveStatusList);
}
}
