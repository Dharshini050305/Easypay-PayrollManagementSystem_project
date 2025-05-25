package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entities.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll,Integer>{

}
