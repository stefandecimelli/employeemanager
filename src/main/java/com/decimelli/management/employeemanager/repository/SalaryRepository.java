package com.decimelli.management.employeemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decimelli.management.employeemanager.model.Salary;
import com.decimelli.management.employeemanager.model.SalaryId;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {
	
}