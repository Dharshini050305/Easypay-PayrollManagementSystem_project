package com.hexaware.easypay.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.Attendance;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance ,Integer>{
	
	// Custom query to fetch attendance by employee ID and month
    @Query("SELECT a FROM Attendance a WHERE a.employee.empId = :empId AND FUNCTION('MONTH', a.workDate) = :month")
    List<Attendance> findByEmployeeIdAndMonth(@Param("empId") int empId, @Param("month") int month);

}
