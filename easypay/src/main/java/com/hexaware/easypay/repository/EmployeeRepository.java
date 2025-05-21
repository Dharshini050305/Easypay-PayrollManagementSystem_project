package com.hexaware.easypay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entity.Employee;

@Repository
public class EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
