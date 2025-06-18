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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.service.ManagerServiceImpl;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	@Autowired
	private ManagerServiceImpl managerService;
	
	Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	
	//Review Team Payroll
	@GetMapping("/teampayrolls/{managerId}")
	@PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<List<PayrollDTO>> reviewTeamPayroll(@PathVariable int managerId) {

	    List<PayrollDTO> payrollDTOs = managerService.reviewTeamPayrolls(managerId);

	    logger.info("Team payroll retrieval successful for Manager ID: {}", managerId);
	    return new ResponseEntity<>(payrollDTOs, HttpStatus.OK);
	}

	
	
	@PutMapping("/approveleave/{managerId}/{leaveId}")
	@PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<LeaveRequestDTO> approveRejectLeaveRequest(
	        @PathVariable int managerId,
	        @PathVariable int leaveId,
	        @RequestParam String status) {

	    LeaveRequestDTO updatedLeaveDTO = managerService.updateLeaveStatus(leaveId, status, managerId);

	    logger.info("Approval/Rejection of Leave Request Successful");
	    return ResponseEntity.ok(updatedLeaveDTO);
	}
	@GetMapping("/manager-leaves/{managerId}")
	@PreAuthorize("hasAuthority('MANAGER') ")
	public ResponseEntity<List<LeaveRequestDTO>> getAllLeavesByManager(@PathVariable int managerId) {
	    List<LeaveRequestDTO> leaveList = managerService.getLeaveRequestsByManagerId(managerId);

	    if (leaveList.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.ok(leaveList);
	}

}