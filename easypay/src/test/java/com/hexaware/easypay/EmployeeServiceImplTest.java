package com.hexaware.easypay;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.easypay.dto.AttendanceDTO;
import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Attendance;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.repository.AttendanceRepo;
import com.hexaware.easypay.repository.EmployeeRepo;
import com.hexaware.easypay.repository.LeaveRequestRepo;
import com.hexaware.easypay.repository.PayrollRepo;
import com.hexaware.easypay.service.EmployeeService;

@SpringBootTest

public class EmployeeServiceImplTest {
	@SuppressWarnings("unused")
	@Autowired
    private EmployeeRepo employeeRepo;

    @SuppressWarnings("unused")
	@Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private PayrollRepo payrollRepo;

    @SuppressWarnings("unused")
	@Autowired
    private LeaveRequestRepo leaveRepo;

    @Autowired
    private EmployeeService service; 

    private static Employee testEmployee;
    @BeforeAll
    static void setup(@Autowired EmployeeRepo employeeRepo) {
        testEmployee = new Employee();
        testEmployee.setEmployeeName("Tester");
        testEmployee.setPosition("Dev");
        testEmployee.setEmail("tester@example.com");
        testEmployee.setEmployeeSalary(50000);
        testEmployee.setJoinDate(LocalDate.now());
        employeeRepo.save(testEmployee);
    }

    @Test
    @Order(1)
    void testSubmitAttendance() {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setWorkDate(LocalDate.now());
        dto.setHoursWorked(8);
        dto.setStatus("Present");

        Attendance attendance = service.submitAttendance(testEmployee.getEmployeeId(), dto);
        assertNotNull(attendance);
        assertEquals("Present", attendance.getAttendedStatus());
    }

    @Test
    @Order(2)
    void testRequestLeave() {
        LeaveRequestDTO dto = new LeaveRequestDTO();
        dto.setStartDate(LocalDate.now().plusDays(1));
        dto.setEndDate(LocalDate.now().plusDays(3));
        dto.setLeaveType("Sick");
        dto.setManagerId(0); 

        LeaveRequest leaveRequest = service.requestLeave(testEmployee.getEmployeeId(), dto);
        assertNotNull(leaveRequest);
        assertEquals("Pending", leaveRequest.getLeaveStatus());
        assertEquals("Sick", leaveRequest.getLeaveType());
    }

    @Test
    @Order(3)
    void testGetLeaveRequestsByEmployeeId() {
        List<LeaveRequestDTO> leaves = service.getLeaveRequestsByEmployeeId(testEmployee.getEmployeeId());
        assertFalse(leaves.isEmpty());
        assertEquals(testEmployee.getEmployeeId(), leaves.get(0).getEmployeeId());
    }

    @Test
    @Order(4)
    void testGetPayStubs() {
        // Prepare Payroll
        Payroll payroll = new Payroll();
        payroll.setEmployee(testEmployee);
        payroll.setPayrollDate(LocalDate.now());
        payroll.setBenefits(1000);
        payroll.setDeductions(300);
        payroll.setGrossPay(20000);
        payroll.setNetPay(18700);
        payrollRepo.save(payroll);

        List<PayrollDTO> payStubs = service.getPayStubs(testEmployee.getEmployeeId());
        assertFalse(payStubs.isEmpty());
        assertEquals(testEmployee.getEmployeeId(), payStubs.get(0).getEmployeeId());
    }
}

