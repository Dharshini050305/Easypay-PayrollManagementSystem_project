package com.hexaware.easypay.services;

import java.util.List;

import com.hexaware.easypay.dto.AttendanceDTO;
import com.hexaware.easypay.dto.EmpDTO;
import com.hexaware.easypay.dto.LeavesDTO;
import com.hexaware.easypay.entities.Attendance;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;

public interface IEmployeeService {
	Employee updatePersonalInformation(int employeeId, EmpDTO updatedInfo);

    List<Payroll> getPayStubs(int employeeId);

    Attendance submitAttendance(int employeeId, AttendanceDTO attendancedto);

    Leaves requestLeave(int employeeId, LeavesDTO leavesDto);

}
