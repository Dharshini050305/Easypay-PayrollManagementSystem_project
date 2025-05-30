package com.hexaware.easypay.services;

import java.util.List;


import com.hexaware.easypay.dto.AttendanceDto;
import com.hexaware.easypay.dto.EmpDto;
import com.hexaware.easypay.dto.LeavesDto;
import com.hexaware.easypay.entities.Attendance;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;

public interface IEmployeeService {
	
	Employee updatePersonalInformation(int employeeId, EmpDto updatedInfo);

    List<Payroll> getPayStubs(int employeeId);

    Attendance submitAttendance(int employeeId, AttendanceDto attendancedto);

    Leaves requestLeave(int employeeId, LeavesDto leavesDto);

}
