package com.hexaware.easypay.service;
/**
 * Service interface for managing Manager operations in the Payroll Management System.
 * @author Dharshini
 * @version 1.0
 */
import java.util.List;

import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;

public interface ManagerService {
	List<PayrollDTO> reviewTeamPayrolls(int managerId);

	LeaveRequestDTO updateLeaveStatus(int leaveId, String status, int managerId);
	List<LeaveRequestDTO> getLeaveRequestsByManagerId(int managerId);
}
