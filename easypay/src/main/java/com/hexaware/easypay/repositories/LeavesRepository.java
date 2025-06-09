package com.hexaware.easypay.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Leaves;

import jakarta.transaction.Transactional;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves ,Integer>{
	
	@Transactional
//	@Query("select l from Leaves l where l.manager.empId =:managerId")
	List <Leaves> findByManagerEmpId(int managerId);

	 Leaves findByLeaveId(int leaveId);
}