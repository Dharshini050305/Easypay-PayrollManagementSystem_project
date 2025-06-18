package com.hexaware.easypay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entity.LeaveRequest;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest ,Integer>{
	List<LeaveRequest> findByEmployeeEmployeeId(int employeeId);
    List<LeaveRequest> findByManagerEmployeeId(int managerId);
	@Query("select l from LeaveRequest l where l.manager.employeeId =:managerId")
	List <LeaveRequest> findByManagerEmpId(int managerId);
	

}