package com.hexaware.easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entity.Deductions;

@Repository
public interface DeductionsRepo extends JpaRepository<Deductions, Integer>{

  
    @Query("SELECT d.deductionAmount FROM Deductions d WHERE d.deductionId = " +
           "(SELECT e.deduction.deductionId FROM Employee e WHERE e.employeeId = :employeeId)")
    Double findDeductionAmountByEmployeeId(@Param("employeeId") int employeeId);

}