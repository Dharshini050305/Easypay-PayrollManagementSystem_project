package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.easypay.entities.PayrollPolicy;

public interface PayrollPolicyRepository extends JpaRepository<PayrollPolicy,Integer> {

}
