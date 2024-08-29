package com.decimelli.management.employeemanager.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.DepartmentAssignment;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.repository.DepartmentAssignmentRepository;
import com.decimelli.management.employeemanager.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository repository;

	@Autowired
	DepartmentAssignmentRepository assignments;

	public void createDepartment(String name) {
		Department department = new Department();
		department.setName(name);
		repository.save(department);
	}

	public void assignEmployeeToDepartment(Employee employee, Department department, Date startDate) {
		assignEmployeeToDepartment(employee, department, startDate, Date.valueOf("9999-1-1"));
	}

	public void assignEmployeeToDepartment(Employee employee, Department department, Date from, Date to) {
		DepartmentAssignment assignment = new DepartmentAssignment();
		assignment.setEmployee(employee);
		assignment.setDepartment(department);
		assignment.setFromDate(from);
		assignment.setToDate(to);

		assignments.save(assignment);
	}

}
