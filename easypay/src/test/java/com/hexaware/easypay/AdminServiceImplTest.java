package com.hexaware.easypay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Benefits;
import com.hexaware.easypay.entity.Deductions;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.Payroll;
import com.hexaware.easypay.entity.Role;
import com.hexaware.easypay.entity.User;
import com.hexaware.easypay.repository.BenefitsRepo;
import com.hexaware.easypay.repository.DeductionsRepo;
import com.hexaware.easypay.repository.EmployeeRepo;
import com.hexaware.easypay.repository.UserRepo;
import com.hexaware.easypay.service.AdminService;


@ActiveProfiles("test")

@SpringBootTest
public class AdminServiceImplTest {
	
	 @Autowired
	 private AdminService adminService;
	

	    @Autowired
	    private UserRepo userRepo;

	    @Autowired
	    private DeductionsRepo deductionsRepo;

	    @Autowired
	    private BenefitsRepo benefitsRepo;

	    @Autowired
	    private EmployeeRepo employeeRepo;
	 
	 @Test
	 @Order(1)
	 void testAddEmployee() {
		
		 User user = new User();
		
		    user.setUserName("testUser");
		    user.setPassword("Password@123");
		    user.setRole(Role.EMPLOYEE);
		    userRepo.save(user);  

		
		    Deductions deduction = new Deductions();
		    deduction.setDeductionAmount(1500);
		    deduction.setDeductionName("Donation");
		    deductionsRepo.save(deduction);

		  
		    Benefits benefit = new Benefits();
		    benefit.setBenefitAmount(2000);
		    benefit.setBenefitName("Allowance");
		    benefitsRepo.save(benefit);

		
		    EmployeeDTO dto = new EmployeeDTO();
		    dto.setUserId(user.getUserId());
		    dto.setEmployeeName("John Doe");
		    dto.setPosition("Developer");
		    dto.setEmail("john@gmail.com");
		    dto.setEmployeeSalary(50000);
		    dto.setJoinDate(LocalDate.now());
		    dto.setDeductionId(deduction.getDeductionId());
		    dto.setBenefitId(benefit.getBenefitId());
		    dto.setManagerId(1);

		   
		    Employee savedEmployee = adminService.addEmployee(dto);
		    assertNotNull(savedEmployee);
		    assertEquals("John Doe", savedEmployee.getEmployeeName());
		}
	 @Test
	 @Order(2)
	 void testUpdateEmployee() {

	     int employeeId = 33;

	     Employee existingEmployee = employeeRepo.findById(employeeId)
	         .orElseThrow(() -> new RuntimeException("Employee not found for test"));

	   
	     Employee updatedEmployee = new Employee();
	     updatedEmployee.setEmployeeName("Updated Name");
	     updatedEmployee.setPosition("Updated Position");
	     updatedEmployee.setEmail("updated.email@example.com");
	     updatedEmployee.setEmployeeSalary(existingEmployee.getEmployeeSalary() + 5000); // increase salary
	     updatedEmployee.setJoinDate(existingEmployee.getJoinDate().plusDays(1)); // example update

	  
	     updatedEmployee.setManager(existingEmployee.getManager());
	     updatedEmployee.setDeduction(existingEmployee.getDeduction());
	     updatedEmployee.setBenefit(existingEmployee.getBenefit());
	     updatedEmployee.setUser(existingEmployee.getUser());

	  
	     Employee result = adminService.updateEmployee(employeeId, updatedEmployee);

	  
	     assertNotNull(result);
	     assertEquals("Updated Name", result.getEmployeeName());
	     assertEquals("Updated Position", result.getPosition());
	     assertEquals("updated.email@example.com", result.getEmail());
	     assertEquals(existingEmployee.getEmployeeSalary() + 5000, result.getEmployeeSalary());
	     assertEquals(existingEmployee.getDeduction(), result.getDeduction());
	     assertEquals(existingEmployee.getBenefit(), result.getBenefit());
	     assertEquals(existingEmployee.getManager(), result.getManager());
	     assertEquals(existingEmployee.getUser(), result.getUser());
	 }
	 @Test
	 @Order(3)
	 void testDeleteEmployee() {
	     int employeeId = 33;

	     
	     assertTrue(employeeRepo.existsById(employeeId));

	  
	     adminService.deleteEmployee(employeeId);

	     
	     assertFalse(employeeRepo.existsById(employeeId));
	 }
	 @Test
	 @Order(4)
	 void testGetEmployeeById() {
	     int employeeId = 33; 

	     EmployeeDTO dto = adminService.getEmployeeById(employeeId);

	     assertNotNull(dto);
	     assertEquals(employeeId, dto.getEmployeeId());
	     assertNotNull(dto.getEmployeeName());
	     assertNotNull(dto.getEmail());
	 }
	 @Test
	 @Order(5)
	 void testGetAllEmployees() {
	     List<EmployeeDTO> employees = adminService.getAllEmployees();

	     assertNotNull(employees);
	     assertFalse(employees.isEmpty());

	   
	     EmployeeDTO first = employees.get(0);
	     assertNotNull(first.getEmployeeName());
	     assertNotNull(first.getEmail());
	 }
	 private static Employee testEmployee;
	 @BeforeAll
	    static void setup(@Autowired EmployeeRepo employeeRepo) {
	      
	        testEmployee = new Employee();
	        testEmployee.setEmployeeName("Test Employee");
	        testEmployee.setPosition("Developer");
	        testEmployee.setEmail("test@company.com");
	        testEmployee.setEmployeeSalary(50000);
	        testEmployee.setJoinDate(LocalDate.now());

	        employeeRepo.save(testEmployee);
	    }
	 @Test
	    @Order(1)
	    void testAddPayroll() {
	        PayrollDTO dto = new PayrollDTO();
	        dto.setEmployeeId(testEmployee.getEmployeeId());
	        dto.setBenefits(2000);
	        dto.setDeductions(500);
	        dto.setGrossPay(25000);
	        dto.setNetPay(23000);
	        dto.setPayrollDate(LocalDate.now());

	        Payroll payroll = adminService.addPayroll(dto);

	        assertNotNull(payroll);
	        assertEquals(2000, payroll.getBenefits());
	        assertEquals(testEmployee.getEmployeeId(), payroll.getEmployee().getEmployeeId());
	    }

	    @Test
	    @Order(2)
	    void testUpdatePayroll() {
	       
	        PayrollDTO dto = new PayrollDTO();
	        dto.setEmployeeId(testEmployee.getEmployeeId());
	        dto.setBenefits(2000);
	        dto.setDeductions(500);
	        dto.setGrossPay(25000);
	        dto.setNetPay(23000);
	        dto.setPayrollDate(LocalDate.now());

	        Payroll payroll = adminService.addPayroll(dto);

	   
	        PayrollDTO updateDto = new PayrollDTO();
	        updateDto.setEmployeeId(testEmployee.getEmployeeId());
	        updateDto.setBenefits(2500);
	        updateDto.setDeductions(600);
	        updateDto.setGrossPay(26000);
	        updateDto.setNetPay(24000);
	        updateDto.setPayrollDate(LocalDate.now().plusDays(1));

	        Payroll updated = adminService.updatePayroll(payroll.getPayrollId(), updateDto);

	        assertNotNull(updated);
	        assertEquals(2500, updated.getBenefits());
	        assertEquals(600, updated.getDeductions());
	        assertEquals(26000, updated.getGrossPay());
	    }

	    @Test
	    @Order(3)
	    void testGetPayrollById() {
	        PayrollDTO dto = new PayrollDTO();
	        dto.setEmployeeId(testEmployee.getEmployeeId());
	        dto.setBenefits(2000);
	        dto.setDeductions(500);
	        dto.setGrossPay(25000);
	        dto.setNetPay(23000);
	        dto.setPayrollDate(LocalDate.now());

	        Payroll payroll = adminService.addPayroll(dto);

	        PayrollDTO found = adminService.getPayrollById(payroll.getPayrollId());

	        assertNotNull(found);
	        assertEquals(payroll.getPayrollId(), found.getPayrollId());
	    }

	    @Test
	    @Order(4)
	    void testGetAllPayrolls() {
	        List<PayrollDTO> payrollList = adminService.getAllPayroll();
	        assertTrue(payrollList.size() > 0);
	    }

	   
	}


