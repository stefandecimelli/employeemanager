package com.decimelli.management.employeemanager.repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.DepartmentAssignment;
import com.decimelli.management.employeemanager.model.DepartmentManagement;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.model.Salary;
import com.decimelli.management.employeemanager.model.Title;
import com.decimelli.management.employeemanager.service.ManagerService;
import com.decimelli.management.employeemanager.service.RetirementService;

@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employees;

	@Autowired
	SalaryRepository salaries;

	@Autowired
	DepartmentRepository departments;

	@Autowired
	DepartmentAssignmentRepository assignments;

	@Autowired
	ManagerService managements;

	@Autowired
	RetirementService retirement;

	@Test
	void createEmployeeThenSalary() {
		Employee employee = new Employee();
		employee.setBirthDate(Date.valueOf("1997-1-29"));
		employee.setFirstName("Stefan");
		employee.setLastName("Decimelli");
		employee.setGender('M');
		employee.setHireDate(Date.valueOf("2020-7-07"));
		employee.setId(10192459);

		employees.save(employee);

		Salary salary = new Salary();
		salary.setFromDate(Date.valueOf("2020-7-07"));
		salary.setToDate(Date.valueOf("9999-1-1"));
		salary.setSalary(9999);
		salary.setEmployee(employee);

		salaries.save(salary);
	}

	@Test
	void createEmployeeWishSalary() {
		Employee employee = new Employee();
		employee.setBirthDate(Date.valueOf("1999-2-13"));
		employee.setFirstName("Alex");
		employee.setLastName("Decimelli");
		employee.setGender('M');
		employee.setHireDate(Date.valueOf("2020-7-07"));
		employee.setId(20192459);

		Salary salary = new Salary();
		salary.setFromDate(Date.valueOf("2020-7-07"));
		salary.setToDate(Date.valueOf("9999-1-1"));
		salary.setSalary(17_500);

		employee.addSalary(salary);
		employees.save(employee);
	}

	@Test
	void testGetEmployeeByName() {
		employees.getEmployeeByName("Stefan", "Decimelli", PageRequest.of(0, 20))
				.forEach(System.out::println);
	}

	@Test
	void testGetEmployeeByFirstName() {
		employees.getEmployeeByName("Stefan", null, PageRequest.of(0, 20))
				.forEach(System.out::println);
	}

	@Test
	void testGetEmployeeByLasttName() {
		employees.getEmployeeByName(null, "Decimelli", PageRequest.of(0, 20))
				.forEach(System.out::println);
	}

	@Test
	void testGetEmployeeHireData() {
		employees.getEmployeeHireData("Stefan", "Decimelli", null)
				.forEach(System.out::println);
	}

	@Test
	void testEmployeeAddNewSalary() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		Salary salary = new Salary();
		salary.setFromDate(Date.valueOf("2024-5-07"));
		salary.setToDate(Date.valueOf("9999-1-1"));
		salary.setSalary(35000);

		employee.addSalary(salary);
		employees.save(employee);
	}

	@Test
	void testEmployeeAddNewTitle() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		Title title = new Title();
		title.setFromDate(Date.valueOf("2024-7-7"));
		title.setToDate(Date.valueOf("9999-1-1"));
		title.setTitle("Software Developer");

		employee.addTitle(title);
		employees.save(employee);
	}

	@Test
	void testEmployeeAddDepartment() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		Department department = departments.findById("d004").get();

		DepartmentAssignment assignment = new DepartmentAssignment();
		assignment.setEmployee(employee);
		assignment.setDepartment(department);
		assignment.setFromDate(Date.valueOf("1999-01-01"));
		assignment.setToDate(Date.valueOf("9999-1-1"));

		assignments.save(assignment);
	}

	@Test
	void assignManagement() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		managements.makeAsManager(employee, Date.valueOf("1999-01-01"), Date.valueOf("9999-1-1"));
	}

	@Test
	void getManagedDepartmentByDepartmentId() {
		Department department = departments.findById("d004").get();
		managements.getDepartmentManagerHistory(department)
				.forEach(System.out::println);
	}

	@Test
	void getManagedDepartmentByEmployeeId() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		managements.getManagedDepartmentHistory(employee)
				.forEach(System.out::println);
	}

	@Test
	void testRetireEmployee() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		retirement.retireEmployee(employee, new Date(Calendar.getInstance().getTimeInMillis()));
		employees.getEmployeeByName("Stefan", "Decimelli", null)
				.forEach(System.out::println);
	}
}
