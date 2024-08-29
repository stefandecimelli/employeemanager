package com.decimelli.management.employeemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.DepartmentProjection;

public interface DepartmentRepository extends
		ListCrudRepository<Department, String>,
		ListPagingAndSortingRepository<Department, String> {

	@Query(nativeQuery = true, value = "select dept_no as id, dept_name as name from departments")
	public List<DepartmentProjection> getAllDepartments();
}
