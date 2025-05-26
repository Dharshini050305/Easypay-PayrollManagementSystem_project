package com.hexaware.easypay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.easypay.dto.AttendanceDTO;
import com.hexaware.easypay.dto.EmpDTO;
import com.hexaware.easypay.dto.LeavesDTO;
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

public class EmployeeServiceImpl implements IEmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository ;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private LeavesRepository leavesRepository;
	
	@Autowired
	private PayrollRepository payrollRepository;
	
	
	

	public Employee updatePersonalInformation(int employeeId, EmpDTO updatedInfo) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id: "+employeeId+" not found"));
        employee.setEmployeeName(updatedInfo.getEmployeeName());
        employee.setEmployeeDepartment(updatedInfo.getEmployeeDepartment());
        employee.setPosition(updatedInfo.getPosition());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Payroll> getPayStubs(int employeeId) {
    	
        List<Payroll> payroll = payrollRepository.findByEmployeeEmpId(employeeId);
        
        if(payroll.isEmpty()) {
        	throw new PayrollNotFoundException("No Payroll records found for Employee Id:"+employeeId); 	
        }
        return payroll;
    }

    @Override
    public Attendance submitAttendance(int employeeId, AttendanceDTO attendanceDTO) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with Id: " + employeeId + " not found"));
        
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee); 
        attendance.setWorkDate(attendanceDTO.getWorkDate());
        attendance.setHoursWorked(attendanceDTO.getHoursWorked());
        attendance.setAttendanceStatus(attendanceDTO.getStatus());
        return attendanceRepository.save(attendance);
    }

    @Override
    public Leaves requestLeave(int employeeId, LeavesDTO leavesDto) {
    	
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
