package com.hexaware.easypay.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.AttendanceDTO;
import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Attendance;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.exception.EmployeeNotFoundException;
import com.hexaware.easypay.repository.AttendanceRepo;
import com.hexaware.easypay.repository.BenefitsRepo;
import com.hexaware.easypay.repository.DeductionsRepo;
import com.hexaware.easypay.repository.EmployeeRepo;
import com.hexaware.easypay.repository.LeaveRequestRepo;
import com.hexaware.easypay.repository.PayrollRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepo employeeRepository ;
	
	@Autowired
	private AttendanceRepo attendanceRepository;
	
	@Autowired
	private LeaveRequestRepo leavesRepository;
	
	@Autowired
	private PayrollRepo payrollRepository;
	
	

	@Override
	public List<PayrollDTO> getPayStubs(int employeeId) {
	    List<Payroll> payrolls = payrollRepository.searchByEmployeeEmployeeId(employeeId);  

	    return payrolls.stream()
	        .map(payroll -> {
	            PayrollDTO dto = new PayrollDTO();
	            dto.setPayrollId(payroll.getPayrollId());
	            dto.setBenefits(payroll.getBenefits());
	            dto.setDeductions(payroll.getDeductions());
	            dto.setGrossPay(payroll.getGrossPay());
	            dto.setNetPay(payroll.getNetPay());
	            dto.setPayrollDate(payroll.getPayrollDate());
	            dto.setEmployeeId(employeeId); // already known
	            return dto;
	        })
	        .collect(Collectors.toList());
	}
    
    @Override
	public Attendance submitAttendance(int employeeId, AttendanceDTO attendanceDTO) {
		Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with Id: " + employeeId + " not found"));
        
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee); 
        attendance.setWorkDate(attendanceDTO.getWorkDate());
        attendance.setHoursWorked(attendanceDTO.getHoursWorked());
        attendance.setAttendedStatus(attendanceDTO.getStatus());
        return attendanceRepository.save(attendance);
	}

   
    @Override
    public LeaveRequest requestLeave(int employeeId, LeaveRequestDTO leaveRequestDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with Id:" + employeeId + " not found"));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
        leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
        leaveRequest.setLeaveType(leaveRequestDTO.getLeaveType()); 
        leaveRequest.setLeaveStatus("Pending");
        leaveRequest.setEmployee(employee);

        // ✅ Fetch and set the manager if managerId is present
        if (leaveRequestDTO.getManagerId() != 0) {
            Employee manager = employeeRepository.findById(leaveRequestDTO.getManagerId())
                    .orElseThrow(() -> new EmployeeNotFoundException("Manager with ID " + leaveRequestDTO.getManagerId() + " not found"));
            leaveRequest.setManager(manager);
        }

        return leavesRepository.save(leaveRequest);
    }

@Override
public List<LeaveRequestDTO> getLeaveRequestsByEmployeeId(int employeeId) {
    List<LeaveRequest> leaveRequests = leavesRepository.findByEmployeeEmployeeId(employeeId);

    return leaveRequests.stream().map(leave -> new LeaveRequestDTO(
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
