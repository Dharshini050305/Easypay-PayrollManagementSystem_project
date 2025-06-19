package com.hexaware.easypay;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.easypay.dto.LeaveRequestDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.repository.EmployeeRepo;
import com.hexaware.easypay.repository.LeaveRequestRepo;
import com.hexaware.easypay.repository.PayrollRepo;
import com.hexaware.easypay.service.ManagerService;

@SpringBootTest
public class ManagerServiceImplTest {
	@SuppressWarnings("unused")
	@Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PayrollRepo payrollRepo;

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    @Autowired
    private ManagerService managerService;

    private static Employee manager;
    private static Employee teamMember;
    private static Payroll payroll;
    private static LeaveRequest leaveRequest;

    @BeforeAll
    static void setup(@Autowired EmployeeRepo employeeRepo) {
        manager = new Employee();
        manager.setEmployeeName("Manager");
        manager.setPosition("Manager");
        manager.setEmail("manager@example.com");
        manager.setEmployeeSalary(80000);
        manager.setJoinDate(LocalDate.now());
        employeeRepo.save(manager);

        teamMember = new Employee();
        teamMember.setEmployeeName("Team Member");
        teamMember.setPosition("Dev");
        teamMember.setEmail("team@example.com");
        teamMember.setEmployeeSalary(50000);
        teamMember.setJoinDate(LocalDate.now());
        teamMember.setManager(manager);
        employeeRepo.save(teamMember);
    }

    @Test
    @Order(1)
    void testReviewTeamPayrolls() {
        payroll = new Payroll();
        payroll.setEmployee(teamMember);
        payroll.setGrossPay(20000);
        payroll.setNetPay(18000);
        payroll.setBenefits(1000);
        payroll.setDeductions(300);
        payroll.setPayrollDate(LocalDate.now());
        payrollRepo.save(payroll);

        List<PayrollDTO> result = managerService.reviewTeamPayrolls(manager.getEmployeeId());
        assertFalse(result.isEmpty());
        assertEquals(teamMember.getEmployeeId(), result.get(0).getEmployeeId());
    }

    @Test
    @Order(2)
    void testUpdateLeaveStatus() {
        leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(teamMember);
        leaveRequest.setStartDate(LocalDate.now().plusDays(2));
        leaveRequest.setEndDate(LocalDate.now().plusDays(3));
        leaveRequest.setLeaveType("Casual");
        leaveRequest.setLeaveStatus("Pending");
        leaveRequestRepo.save(leaveRequest);

        LeaveRequestDTO updated = managerService.updateLeaveStatus(leaveRequest.getLeaveId(), "Approved", manager.getEmployeeId());
        assertNotNull(updated);
        assertEquals("Approved", updated.getLeaveStatus());
        assertEquals(manager.getEmployeeId(), updated.getManagerId());
    }

    @Test
    @Order(3)
    void testGetLeaveRequestsByManagerId() {
        List<LeaveRequestDTO> leaves = managerService.getLeaveRequestsByManagerId(manager.getEmployeeId());
        assertFalse(leaves.isEmpty());
        assertEquals(manager.getEmployeeId(), leaves.get(0).getManagerId());
    }
}