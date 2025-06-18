package com.hexaware.easypay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.hexaware.easypay.dto.EmployeeDeductionmicro;
import com.hexaware.easypay.dto.EmployeeDeductionVO;
import com.hexaware.easypay.entity.Deductions;
import com.hexaware.easypay.repository.EmployeeRepo;

@Service
public class DeductionServiceImpl implements DeductionService {
	@Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public EmployeeDeductionVO getEmployeeAndDeductionById(int employeeId) {
        // Step 1: Get employee entity from DB
        EmployeeDeductionmicro empDto = employeeRepo.findEmployeeDetailsById(employeeId);
               
        // Step 2: Fetch Deduction using RestTemplate
        Deductions deduction = restTemplate.getForObject(
                "http://localhost:8080/api/duduction/getdeductionbyid/" + empDto.getDeductionId(), Deductions.class);

        // Step 3: Map to EmployeeDTO
        // Map to EmployeeRoleVO
        EmployeeDeductionVO employeeDeductionVO = new EmployeeDeductionVO(empDto, deduction);
        employeeDeductionVO.setEmployee(empDto);
        employeeDeductionVO.setDeduction(deduction);

        return employeeDeductionVO;
}
}
