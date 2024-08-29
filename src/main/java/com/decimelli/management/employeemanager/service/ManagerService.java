package com.decimelli.management.employeemanager.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.DepartmentAssignment;
import com.decimelli.management.employeemanager.model.DepartmentManagement;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.repository.DepartmentAssignmentRepository;
import com.decimelli.management.employeemanager.repository.DepartmentManagementRepository;

@Service
public class ManagerService {

	@Autowired
	DepartmentManagementRepository repository;

	@Autowired
	DepartmentAssignmentRepository assignments;

	@Autowired
	DepartmentService departmentService;

	public List<DepartmentManagement> getManagedDepartmentHistory(Employee employee) {
		return repository.getManagedDepartment(employee.getId());
	}

	public List<DepartmentManagement> getDepartmentManagerHistory(Department department) {
		return repository.getDepartmentManager(department.getId());
	}

	public void makeAsManager(Employee employee, Department department, Date startDate) {
		makeAsManager(employee, department, startDate, Date.valueOf("9999-1-1"));
	}

	public void makeAsManager(Employee employee, Department department, Date fromDate, Date toDate) {
		for (DepartmentAssignment assignment : employee.getDepartmentHistory()) {
			if (assignment.getToDate().compareTo(fromDate) > 0) {
				assignment.setToDate(fromDate);
				assignments.save(assignment);
			}
		}
		
		DepartmentManagement management = new DepartmentManagement();
		management.setDepartment(department);
		management.setEmployee(employee);
		management.setFromDate(fromDate);
		management.setToDate(toDate);

		repository.save(management);
		departmentService.assignEmployeeToDepartment(employee, department, fromDate, toDate);
	}

	public void removeAsManager(Employee employee, Date lastDayAsManager) {
		for (DepartmentManagement management : getManagedDepartmentHistory(employee)) {
			if (management.getToDate().compareTo(lastDayAsManager) > 0) {
				management.setToDate(lastDayAsManager);
				repository.save(management);
			}
		}
	}

}
