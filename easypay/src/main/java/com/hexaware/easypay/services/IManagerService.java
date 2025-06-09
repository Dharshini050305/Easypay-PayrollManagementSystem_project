package com.hexaware.easypay.services;

import java.util.List;

import com.hexaware.easypay.dto.LeaveListDto;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;

public interface IManagerService {
	

	List<PayrollDTO> reviewTeamPayrolls(int managerId);

	LeaveListDto approveLeaveRequest(int managerId, int leaveId, String status);

	List<LeaveListDto> getLeavesByManagerId(int managerId);
    
    
}