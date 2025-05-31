package com.hexaware.easypay.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll,Integer>{
	
	// Custom method to find payrolls for a specific employee
    List<Payroll> findByEmployeeEmployeeId(int employeeId);

    
    @Query("SELECT p FROM Payroll p WHERE p.employee.manager.employeeId = :managerId")
    List<Payroll> findByEmployeeManagerEmployeeId( int managerId);
    
    @Query("SELECT p FROM Payroll p WHERE p.payrollDate BETWEEN :startDate AND :endDate")
    List<Payroll> findPayrollsByDateRange( LocalDate startDate, LocalDate endDate);

}
