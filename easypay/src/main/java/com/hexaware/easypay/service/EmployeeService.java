package com.hexaware.easypay.service;

import java.util.List;

import com.hexaware.easypay.dto.AttendanceDTO;
import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Attendance;
import com.hexaware.easypay.entity.LeaveRequest;

public interface EmployeeService {
	

    List<PayrollDTO> getPayStubs(int employeeId);

    Attendance submitAttendance(int employeeId, AttendanceDTO attendanceDTO);

    LeaveRequest requestLeave(int employeeId, LeaveRequestDTO leaverequestDTO);
    List<LeaveRequestDTO> getLeaveRequestsByEmployeeId(int employeeId);
}
