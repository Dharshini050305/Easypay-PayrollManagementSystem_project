package com.hexaware.easypay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.easypay.dto.EmpDTO;
import com.hexaware.easypay.dto.EmpMicroDTO;
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
	    public EmployeeRoleVO getEmployeeAndRoleById(int employeeId) {
	        // Fetch only the necessary fields using the repository query
	        EmpMicroDTO empDto = employeeRepo.findEmployeeDetailsById(employeeId);

	        // Use RestTemplate to get the Role details
	        Role role = restTemplate.getForObject(
	            "http://localhost:8282/api/roles/getrolebyid/" + EmployeeDTO.getRoleId(), Role.class);

	        // Map to EmployeeRoleVO
	        EmployeeRoleVO employeeRoleVo = new EmployeeRoleVO();
	        employeeRoleVo.setEmployee(empDto);
	        employeeRoleVo.setRole(role);

	        return employeeRoleVo;
	    }

}
