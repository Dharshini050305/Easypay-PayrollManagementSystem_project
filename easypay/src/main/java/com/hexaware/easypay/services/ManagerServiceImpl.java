package com.hexaware.easypay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Payroll> reviewTeamPayrolls(int managerId) {
        List<Payroll> payrolls =  payrollRepository.findByEmployeeManagerEmpId(managerId);
        if (payrolls.isEmpty()) {
            throw new PayrollNotFoundException("No payroll records found for manager with ID " + managerId);
        }

        return payrolls;
    }

    @Override
    public Leaves approveLeaveRequest(int managerId, int leaveId, String status) {
        
        Leaves leavesRequest = leaveRequestRepository.findById(leaveId)
        		.orElseThrow(()-> new LeavesRequestException("Leave request with ID " + leaveId + " not found"));
        
        // Check if the manager is authorized
        if (leavesRequest.getManager() == null || leavesRequest.getManager().getEmployeeId() != managerId) {
            throw new UnAuthorizedActionException("Manager with ID " + managerId + " is not authorized to approve/reject this leave request");
        }
        
        leavesRequest.setLeaveStatus(status);
        
        return leaveRequestRepository.save(leavesRequest);
    }
}
