package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.hexaware.easypay.dto.EmpMicroDto;
import com.hexaware.easypay.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	@Query("SELECT new com.hexaware.easypay.dto.EmpMicroDto(e.employeeName, e.employeeDepartment, e.position, e.role.roleID) " +
            "FROM Employee e WHERE e.employeeId = :employeeId")
     EmpMicroDto findEmployeeDetailsById(@Param("employeeId") int employeeId);
}
