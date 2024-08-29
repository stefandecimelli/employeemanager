package com.decimelli.management.employeemanager.service;

import java.sql.Date;
import java.util.Random;

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
		department.setId(new Random().ints(48, 123)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(4)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString()
				.toUpperCase());

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
