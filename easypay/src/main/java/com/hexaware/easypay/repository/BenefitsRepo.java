package com.hexaware.easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // Make sure to import @Param if you use it explicitly
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entity.Benefits;

@Repository
public interface BenefitsRepo extends JpaRepository<Benefits,Integer>{

	// Custom query to find the benefit amount by employee ID
    @Query("SELECT b.benefitAmount FROM Benefits b WHERE b.benefitId = " +
           "(SELECT e.benefit.benefitId FROM Employee e WHERE e.employeeId = :employeeId)")
    Double findBenefitAmountByEmployeeId(@Param("employeeId") int employeeId); 
}