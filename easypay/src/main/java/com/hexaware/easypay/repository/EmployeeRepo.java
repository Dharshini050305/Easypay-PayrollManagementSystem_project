package com.hexaware.easypay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.hexaware.easypay.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

	
	
}
