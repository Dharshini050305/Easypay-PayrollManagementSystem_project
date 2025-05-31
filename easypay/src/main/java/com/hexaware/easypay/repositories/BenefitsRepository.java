package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Benefits;
@Repository
public interface BenefitsRepository extends JpaRepository<Benefits,Integer>{
	  
	// Custom query to find the benefit amount by employee ID
    @Query("SELECT b.benefitAmount FROM Benefits b WHERE b.benefitId = (SELECT e.benefits.benefitId FROM Employee e WHERE e.employeeId = :employeeId)")
    Double findBenefitAmountByEmployeeId( int employeeId);

}
