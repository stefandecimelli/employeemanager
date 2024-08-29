package com.decimelli.management.employeemanager.repository;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.service.DepartmentService;
import com.decimelli.management.employeemanager.service.ManagerService;

@SpringBootTest
public class DepartmentRepositoryTest {

	@Autowired
	EmployeeRepository employees;

	@Autowired
	DepartmentRepository departments;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	ManagerService managements;

	@Autowired
	DepartmentAssignmentRepository assignments;

	@Test
	void createDepartment() {
		departmentService.createDepartment("Software Development");
		departmentService.createDepartment("R&D");
	}

	@Test
	void testGetAllDepartments() {
		departments.getAllDepartments()
			.forEach(System.out::println);
	}

	@Test
	void testEmployeeAddDepartment() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		Department department = departments.getDepartmentByName("Software Development").get(0);
		
		departmentService.assignEmployeeToDepartment(employee, department, Date.valueOf("1999-01-02"));
	}

	@Test
	void assignManagement() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		Department department = departments.getDepartmentByName("R&D").get(0);
		
		managements.makeAsManager(employee, department, Date.valueOf("2007-03-06"));
	}

	@Test
	void getManagedDepartmentByDepartmentName() {
		Department department = departments.getDepartmentByName("R&D").get(0);
		managements.getDepartmentManagerHistory(department)
				.forEach(System.out::println);
	}

	@Test
	void getManagedDepartmentByEmployeeId() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		managements.getManagedDepartmentHistory(employee)
				.forEach(System.out::println);
	}

}
