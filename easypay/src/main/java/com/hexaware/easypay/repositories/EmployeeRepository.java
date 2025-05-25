package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
