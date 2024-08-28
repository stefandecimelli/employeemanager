package com.decimelli.management.employeemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decimelli.management.employeemanager.model.DepartmentAssignment;
import com.decimelli.management.employeemanager.model.DepartmentAssignmentId;

public interface DepartmentAssignmentRepository extends JpaRepository<DepartmentAssignment, DepartmentAssignmentId> {
	
}
