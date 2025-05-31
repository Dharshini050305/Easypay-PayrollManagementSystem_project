package com.hexaware.easypay.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Leaves;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves ,Integer>{
	
	@Query("select l from Leaves l where l.manager.employeeId =:managerId")
	List <Leaves> findByManagerEmpId(int managerId);
}
