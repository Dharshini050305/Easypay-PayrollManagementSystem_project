package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.PayrollPolicy;

@Repository
public interface PayrollPolicyRepository extends JpaRepository<PayrollPolicy,Integer> {
	 PayrollPolicy findBypolicyId(int policyId);

}
