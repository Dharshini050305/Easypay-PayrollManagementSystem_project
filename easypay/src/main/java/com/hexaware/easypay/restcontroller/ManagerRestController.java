package com.hexaware.easypay.restcontroller;

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

import com.hexaware.easypay.dto.LeaveListDto;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;
import com.hexaware.easypay.services.IManagerService;

@RestController
@RequestMapping("/api/managercontroller/")
@CrossOrigin(origins= "http://localhost:4200/")
public class ManagerRestController {
	@Autowired
	private IManagerService managerService;
	
	Logger logger = LoggerFactory.getLogger(ManagerRestController.class);
	
	
	//Review Team Payroll
	
		@GetMapping("/teampayrolls/{managerId}")
		@PreAuthorize("hasAuthority('MANAGER')")
		public ResponseEntity<List<PayrollDTO>> reviewTeamPayroll(@PathVariable int managerId){
		
			List<PayrollDTO> payroll = managerService.reviewTeamPayrolls(managerId);
			
			ResponseEntity<List<PayrollDTO>> responseEntity = new ResponseEntity<>(payroll,HttpStatus.OK);
			logger.info("Team payroll Retrieval Succesfull");
			return responseEntity;
		
		}
		
		
		@GetMapping("/leaves/{managerId}")
		@PreAuthorize("hasAuthority('MANAGER')")
		public ResponseEntity <List<LeaveListDto>> getAllLeaves(@PathVariable int managerId){
			
			List <LeaveListDto> leaves = managerService.getLeavesByManagerId(managerId);
					
			ResponseEntity<List<LeaveListDto>> responseEntity = new ResponseEntity<>(leaves,HttpStatus.OK);
			return responseEntity;
		}

		
		
		
		@PutMapping("/approveleave/{managerId}/{leaveId}")
		@PreAuthorize("hasAuthority('MANAGER')")
		public ResponseEntity<LeaveListDto> approveRejectLeaveRequest(@ PathVariable int managerId, @PathVariable int leaveId, 
				@RequestParam("status") String status){
			
			LeaveListDto updateLeave = managerService.approveLeaveRequest(managerId, leaveId, status);
			
			ResponseEntity<LeaveListDto> responseEntity = new ResponseEntity<>(updateLeave,HttpStatus.OK);
			logger.info("Approval/Rejection of Leave Request Succesfull");
			return responseEntity;
			
		}
		
		

		
		
		
		
	}
