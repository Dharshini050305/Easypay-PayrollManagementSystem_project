package com.hexaware.easypay.services;

import java.util.List;

import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;

public interface IManagerService {
	List<Payroll> reviewTeamPayrolls(int managerId);

    Leaves approveLeaveRequest(int managerId, int leaveId, String status);

}
