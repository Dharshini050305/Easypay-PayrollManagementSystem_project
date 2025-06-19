package com.hexaware.deduction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.deduction.entity.Deductions;

@Repository
public interface DeductionRepo extends JpaRepository<Deductions, Integer> {
   
}