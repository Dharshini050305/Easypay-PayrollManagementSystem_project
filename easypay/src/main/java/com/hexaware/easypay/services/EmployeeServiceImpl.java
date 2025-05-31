package com.hexaware.easypay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.AttendanceDto;
import com.hexaware.easypay.dto.EmpDto;
import com.hexaware.easypay.dto.LeavesDto;
import com.hexaware.easypay.entities.Attendance;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;
import com.hexaware.easypay.exceptions.EmployeeNotFoundException;
import com.hexaware.easypay.exceptions.PayrollNotFoundException;
import com.hexaware.easypay.repositories.AttendanceRepository;
import com.hexaware.easypay.repositories.EmployeeRepository;
import com.hexaware.easypay.repositories.LeavesRepository;
import com.hexaware.easypay.repositories.PayrollRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository ;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private LeavesRepository leavesRepository;
	
	@Autowired
	private PayrollRepository payrollRepository;
	
	
	

	public Employee updatePersonalInformation(int employeeId, EmpDto updatedInfo) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id: "+employeeId+" not found"));
        employee.setEmployeeName(updatedInfo.getEmployeeName());
        employee.setEmployeeDepartment(updatedInfo.getEmployeeDepartment());
        employee.setPosition(updatedInfo.getPosition());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Payroll> getPayStubs(int employeeId) {
    	
        List<Payroll> payroll = payrollRepository.findByEmployeeEmployeeId(employeeId);
        
        if(payroll.isEmpty()) {
        	throw new PayrollNotFoundException("No Payroll records found for Employee Id:"+employeeId); 	
        }
        return payroll;
    }

    @Override
    public Attendance submitAttendance(int employeeId, AttendanceDto attendanceDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with Id: " + employeeId + " not found"));
        
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee); 
        attendance.setWorkDate(attendanceDto.getWorkDate());
        attendance.setHoursWorked(attendanceDto.getHoursWorked());
        attendance.setAttendanceStatus(attendanceDto.getStatus());
        return attendanceRepository.save(attendance);
    }

    @Override
    public Leaves requestLeave(int employeeId, LeavesDto leavesDto) {
    	
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id:"+employeeId+" not found"));
        
        
        Leaves leaves= new Leaves();
        leaves.setStartDate(leavesDto.getStartDate());
        leaves.setEndDate(leavesDto.getEndDate());
        leaves.setLeaveStatus("Pending");
        leaves.setEmployee(employee);
        
        return leavesRepository.save(leaves);
        
        
    }

}
