package com.hexaware.easypay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entity.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance ,Integer>{
	
	// Custom query to fetch attendance by employee ID and month
    @Query("SELECT a FROM Attendance a WHERE a.employee.employeeId = :employeeId AND FUNCTION('MONTH', a.workDate) = :month")
    List<Attendance> findByEmployeeIdAndMonth(@Param("employeeId") int employeeId, @Param("month") int month);

}