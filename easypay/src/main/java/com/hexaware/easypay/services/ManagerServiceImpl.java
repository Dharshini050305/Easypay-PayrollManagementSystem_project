package com.hexaware.easypay.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.LeaveListDto;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;
import com.hexaware.easypay.exceptions.LeavesRequestException;
import com.hexaware.easypay.exceptions.PayrollNotFoundException;
import com.hexaware.easypay.exceptions.UnAuthorizedActionException;
import com.hexaware.easypay.repositories.LeavesRepository;
import com.hexaware.easypay.repositories.PayrollRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements IManagerService{
	@Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private LeavesRepository leaveRequestRepository;
    @Override
	public List<PayrollDTO> reviewTeamPayrolls(int managerId) {
		List<Payroll> payrolls = payrollRepository.findByEmployeeManagerEmpId(managerId);
		if (payrolls.isEmpty()) {
			throw new PayrollNotFoundException("No payroll records found for manager with ID " + managerId);
		}
		List<PayrollDTO> payrollDto = new ArrayList<>();
		for(Payroll payroll: payrolls) {
			payrollDto.add(new PayrollDTO(payroll));
		}
		

		return payrollDto;
	}

	@Override
	public LeaveListDto approveLeaveRequest(int managerId, int leaveId, String status) {

		Leaves leavesRequest = leaveRequestRepository.findByLeaveId(leaveId);
				
		// Check if the manager is authorized
		if (leavesRequest.getManager() == null || leavesRequest.getManager().getEmpId() != managerId) {
			throw new UnAuthorizedActionException(
					"Manager with ID " + managerId + " is not authorized to approve/reject this leave request");
		}

		leavesRequest.setLeaveStatus(status);

		Leaves leave = leaveRequestRepository.save(leavesRequest);
		LeaveListDto leaveDto =  new LeaveListDto(leave.getStartDate(),leave.getEndDate(), leave.getLeaveType(),
				leave.getManager().getEmpId() , leave.getEmployee().getEmpId(),leave.getLeaveStatus(),leave.getLeaveId());

		return leaveDto;
	}

	
	

	@Override
	public List<LeaveListDto> getLeavesByManagerId(int managerId) {

		List<Leaves> leaves = leaveRequestRepository.findByManagerEmpId(managerId);

		List<LeaveListDto> leavesDto = new ArrayList<>();

		leaves.forEach(leave -> {
			LeaveListDto leaveDto =  new LeaveListDto(leave.getStartDate(),leave.getEndDate(), leave.getLeaveType(),
					leave.getManager().getEmpId() , leave.getEmployee().getEmpId(),leave.getLeaveStatus(),leave.getLeaveId() );

			leavesDto.add(leaveDto);

		});

		return leavesDto;
	}

}