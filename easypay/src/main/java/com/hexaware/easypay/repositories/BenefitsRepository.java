package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Benefits;
@Repository
public interface BenefitsRepository extends JpaRepository<Benefits,Integer>{
	  
	
    // Custom query to find the benefit amount by employee ID
    @Query("SELECT b.benefitAmount FROM Benefits b WHERE b.benifitId = (SELECT e.benefits.benifitId FROM Employee e WHERE e.empId = :empId)")
    Double findBenefitAmountByEmployeeId(@Param("empId") int empId);



}