package com.hexaware.easypay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.easypay.entities.ComplianceReport;

@Repository
public interface ComplianceReportRepository extends JpaRepository<ComplianceReport,Integer>{

}
