package com.decimelli.management.employeemanager.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.decimelli.management.employeemanager.model.DepartmentProjection;

@SpringBootTest
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository db;

	@Test
	void testGetAllDepartments() {
		List<DepartmentProjection> departments = db.getAllDepartments();
		departments.forEach(department -> System.out
				.println(String.format("(name=%s, id=%s)", department.getName(), department.getId())));
	}

}
