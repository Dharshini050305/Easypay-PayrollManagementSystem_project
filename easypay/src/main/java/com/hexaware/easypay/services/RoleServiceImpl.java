package com.hexaware.easypay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.hexaware.easypay.dto.EmpMicroDto;
import com.hexaware.easypay.dto.EmployeeRoleVO;
import com.hexaware.easypay.entities.Role;
import com.hexaware.easypay.repositories.EmployeeRepository;

@Service
public class RoleServiceImpl implements IRoleService{
	 @Autowired
	    private EmployeeRepository employeeRepo;
	    
	    @Autowired
	    private RestTemplate restTemplate;

	    @Override
	    public EmployeeRoleVO getEmployeeAndRoleById(int empId) {
	        // Fetch only the necessary fields using the repository query
	        EmpMicroDto empDto = employeeRepo.findEmployeeDetailsById(empId);

	        // Use RestTemplate to get the Role details
	        Role role = restTemplate.getForObject(
	            "http://localhost:8080/api/roles/getrolebyid/" + empDto.getRoleId(), Role.class);

	        // Map to EmployeeRoleVO
	        EmployeeRoleVO employeeRoleVo = new EmployeeRoleVO();
	        employeeRoleVo.setEmployee(empDto);
	        employeeRoleVo.setRole(role);

	        return employeeRoleVo;
	    }
	}
