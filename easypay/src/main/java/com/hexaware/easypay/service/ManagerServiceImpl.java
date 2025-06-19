package com.hexaware.easypay.service;
/**
 * Service implementation for managing Manager operations in the Payroll Management System.
 * @author Dharshini
 * @version 1.0
 */
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.exception.EmployeeNotFoundException;
import com.hexaware.easypay.exception.LeaveRequestNotFoundException;
import com.hexaware.easypay.exception.PayrollNotFoundException;
import com.hexaware.easypay.repository.EmployeeRepo;
import com.hexaware.easypay.repository.LeaveRequestRepo;
import com.hexaware.easypay.repository.PayrollRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
	@Autowired
    private PayrollRepo payrollRepository;

    @Autowired
    private LeaveRequestRepo leaveRequestRepository;
    @Autowired
    private EmployeeRepo employeeRepository;

    @Override
    public List<PayrollDTO> reviewTeamPayrolls(int managerId) {
        List<Payroll> payrolls = payrollRepository.findByEmployeeManagerEmployeeId(managerId);

        if (payrolls.isEmpty()) {
            throw new PayrollNotFoundException("No payroll records found for manager with ID " + managerId);
        }

        return payrolls.stream().map(payroll -> {
            PayrollDTO dto = new PayrollDTO();
            dto.setPayrollId(payroll.getPayrollId());
            dto.setPayrollDate(payroll.getPayrollDate());
            dto.setGrossPay(payroll.getGrossPay());
            dto.setNetPay(payroll.getNetPay());
            dto.setBenefits(payroll.getBenefits());
            dto.setDeductions(payroll.getDeductions());
            dto.setEmployeeId(payroll.getEmployee().getEmployeeId()); // Ensure this relationship exists
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public LeaveRequestDTO updateLeaveStatus(int leaveId, String status, int managerId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
            .orElseThrow(() -> new LeaveRequestNotFoundException("Leave not found with ID: " + leaveId));

        Employee manager = employeeRepository.findById(managerId)
            .orElseThrow(() -> new EmployeeNotFoundException("Manager not found with ID: " + managerId));

        if (!status.equalsIgnoreCase("Approved") && !status.equalsIgnoreCase("Rejected")) {
            throw new IllegalArgumentException("Invalid status. Must be Approved or Rejected");
        }

        leaveRequest.setLeaveStatus(status);
        leaveRequest.setManager(manager);

        LeaveRequest updated = leaveRequestRepository.save(leaveRequest);

        // Convert to DTO
        LeaveRequestDTO dto = new LeaveRequestDTO(
            updated.getLeaveId(),
            updated.getEmployee().getEmployeeId(),
            updated.getStartDate(),
            updated.getEndDate(),
            updated.getLeaveType(),
            updated.getLeaveStatus(),
            updated.getManager().getEmployeeId()
        );

        return dto;
    }
    
    public List<LeaveRequestDTO> getLeaveRequestsByManagerId(int managerId) {
        List<LeaveRequest> leaves = leaveRequestRepository.findByManagerEmployeeId(managerId);

        return leaves.stream().map(leave -> new LeaveRequestDTO(
            leave.getLeaveId(),
            leave.getEmployee().getEmployeeId(),
            leave.getStartDate(),
            leave.getEndDate(),
            leave.getLeaveType(),
            leave.getLeaveStatus(),
            leave.getManager() != null ? leave.getManager().getEmployeeId() : 0
        )).collect(Collectors.toList());
    }


}
