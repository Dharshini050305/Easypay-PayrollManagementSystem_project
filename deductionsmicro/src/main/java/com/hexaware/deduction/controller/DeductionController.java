package com.hexaware.deduction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.deduction.dto.DeductionsDTO;
import com.hexaware.deduction.service.DeductionService;

@RestController
@RequestMapping("/api/deduction")
public class DeductionController {

    @Autowired
    private DeductionService deductionsService;
    @PostMapping
    public ResponseEntity<DeductionsDTO> addDeduction(@RequestBody DeductionsDTO dto) {
        DeductionsDTO savedDeduction = deductionsService.saveDeduction(dto);
        return new ResponseEntity<>(savedDeduction, HttpStatus.CREATED);
    }
    @GetMapping("/getdeductionbyid/{id}")
    public ResponseEntity<DeductionsDTO> getDeductionById(@PathVariable int id) {
        DeductionsDTO dto = deductionsService.getDeductionById(id);
        return ResponseEntity.ok(dto);
    }
}