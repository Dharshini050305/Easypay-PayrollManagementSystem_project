package com.hexaware.easypay.service;
/**
 * Service interface for managing Employee operations in the Payroll Management System.
 * @author Dharshini
 * @version 1.0
 */
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
