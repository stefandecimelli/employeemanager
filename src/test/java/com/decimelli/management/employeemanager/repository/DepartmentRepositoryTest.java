package com.decimelli.management.employeemanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departments;

	@Test
	void testGetAllDepartments() {
		departments.getAllDepartments()
				.forEach(System.out::println);
	}

}
