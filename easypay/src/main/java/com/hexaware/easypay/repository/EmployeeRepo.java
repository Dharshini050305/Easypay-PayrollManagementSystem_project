package com.hexaware.easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.dto.EmployeeDeductionmicro;
import com.hexaware.easypay.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
	
	@Query("SELECT new com.hexaware.easypay.dto.EmployeeDeductionmicro(e.employeeName,  e.position, e.deduction.deductionId) " +
            "FROM Employee e WHERE e.employeeId = :employeeId")
	EmployeeDeductionmicro findEmployeeDetailsById(@Param("employeeId") int employeeId);
}
