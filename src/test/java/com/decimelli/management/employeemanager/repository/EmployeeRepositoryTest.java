package com.decimelli.management.employeemanager.repository;

import java.sql.Date;
import java.util.Calendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.service.ManagerService;
import com.decimelli.management.employeemanager.service.EmployeeService;

@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employees;

	@Autowired
	SalaryRepository salaries;

	@Autowired
	ManagerService managements;

	@Autowired
	EmployeeService employeeService;

	@Test
	void createEmployee() {
		Employee employee = new Employee();
		employee.setBirthDate(Date.valueOf("1997-1-29"));
		employee.setFirstName("Stefan");
		employee.setLastName("Decimelli");
		employee.setGender('M');
		employee.setHireDate(Date.valueOf("2020-7-07"));
		employee.setId(10192459);

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
		employeeService.setNewSalary(employee, 40000, Date.valueOf("2024-08-28"));
		employees.getEmployeeByName("Stefan", "Decimelli", null)
				.forEach(System.out::println);
	}

	@Test
	void testEmployeeAddNewTitle() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		employeeService.setNewTitle(employee, "Software Developer", Date.valueOf("1999-01-02"));
		employeeService.setNewTitle(employee, "Senior Software Engineer", Date.valueOf("2024-12-7"));
		employees.getEmployeeByName("Stefan", "Decimelli", null)
				.forEach(System.out::println);
	}

	@Test
	void testRetireEmployee() {
		Employee employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		employeeService.retireEmployee(employee, new Date(Calendar.getInstance().getTimeInMillis()));
		employees.getEmployeeByName("Stefan", "Decimelli", null)
				.forEach(System.out::println);
	}
}
