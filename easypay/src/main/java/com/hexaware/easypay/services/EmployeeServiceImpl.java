package com.hexaware.easypay.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.easypay.dto.EmpDTO;
import com.hexaware.easypay.dto.LeavesDTO;
import com.hexaware.easypay.entities.Attendance;
import com.hexaware.easypay.entities.Employee;
import com.hexaware.easypay.entities.Leaves;
import com.hexaware.easypay.entities.Payroll;
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
	
	
	

	public Employee updatePersonalInformation(int empId, EmpDTO updatedInfo) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id: "+empId+" not found"));
        employee.setEmpName(updatedInfo.getEmpName());
        employee.setEmpDepartment(updatedInfo.getEmpDepartment());
        employee.setPosition(updatedInfo.getPosition());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Payroll> getPayStubs(int empId) {
    	
        List<Payroll> payroll = payrollRepository.findByEmployeeEmpId(empId);
        
        if(payroll.isEmpty()) {
        	throw new PayrollNotFoundException("No Payroll records found for Employee Id:"+empId); 	
        }
        return payroll;
    }

    @Override
    public Attendance submitAttendance(int empId, AttendanceDto attendanceDto) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with Id: " + empId + " not found"));
        
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee); 
        attendance.setWorkDate(attendanceDto.getWorkDate());
        attendance.setHoursWorked(attendanceDto.getHoursWorked());
        attendance.setAttendanceStatus(attendanceDto.getStatus());
        return attendanceRepository.save(attendance);
    }

    @Override
    public Leaves requestLeave(int empId, LeavesDTO leavesDto) {
    	
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id:"+empId+" not found"));
        
        
        Leaves leaves= new Leaves();
        leaves.setStartDate(leavesDto.getStartDate());
        leaves.setEndDate(leavesDto.getEndDate());
        leaves.setLeaveStatus("Pending");
        leaves.setEmployee(employee);
        
        return leavesRepository.save(leaves);
        
        
    }

}
