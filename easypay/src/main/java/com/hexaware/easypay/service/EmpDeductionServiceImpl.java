package com.hexaware.easypay.service;
/**
 * Service implementation for managing EmployeeDedudctionMicro operations in the Payroll Management System.
 * @author Dharshini
 * @version 1.0
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.easypay.dto.DeductionsDTO;
import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.EmployeeDeductionVO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.repository.EmployeeRepo;

@Service
public class EmpDeductionServiceImpl implements EmpDeductionService{
	@Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RestTemplate restTemplate;

    public EmployeeDeductionVO getDeductionByEmployeeId(int employeeId) {
       
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getPosition(),
                employee.getEmail(),
                employee.getEmployeeSalary(),
                employee.getJoinDate(),
                employee.getUser().getUserId(),
                employee.getRole(),
                employee.getManager() != null ? employee.getManager().getEmployeeId() : null,
                employee.getDeduction().getDeductionId(),
                employee.getBenefit().getBenefitId()
        );

     
        DeductionsDTO deductionDTO = restTemplate.getForObject(
            "http://localhost:8282/api/deduction/getdeductionbyid/" + employeeDTO.getDeductionId(),
            DeductionsDTO.class
        );

      
        return new EmployeeDeductionVO(employeeDTO, deductionDTO);
    }
}