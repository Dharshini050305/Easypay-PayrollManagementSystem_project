package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Deductions;

@Repository
public interface DeductionsRepository extends JpaRepository<Deductions, Integer>{
	
	// Custom query to find deduction amount by employee ID using subquery
    @Query("SELECT d.deductionAmount FROM Deductions d WHERE d.deductionId = " +
           "(SELECT e.deductions.deductionId FROM Employee e WHERE e.empId = :empId)")
    Double findDeductionAmountByEmployeeId(@Param("empId") int empId);
}