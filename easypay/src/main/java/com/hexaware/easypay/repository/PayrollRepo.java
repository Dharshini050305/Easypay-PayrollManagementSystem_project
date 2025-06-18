package com.hexaware.easypay.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.entity.Payroll;

@Repository
public interface PayrollRepo extends JpaRepository<Payroll,Integer>{
	
	// Custom method to find payrolls for a specific employee
    List<Payroll> findByEmployeeEmployeeId(int employeeId);

    
    @Query("SELECT p FROM Payroll p WHERE p.employee.manager.employeeId = :managerId")
    List<Payroll> findByEmployeeManagerEmployeeId( int managerId);
    
    @Query("SELECT p FROM Payroll p WHERE p.payrollDate BETWEEN :startDate AND :endDate")
    List<PayrollDTO> findPayrollsByDateRange( LocalDate startDate, LocalDate endDate);
    List<Payroll> searchByEmployeeEmployeeId(int employeeId);

}