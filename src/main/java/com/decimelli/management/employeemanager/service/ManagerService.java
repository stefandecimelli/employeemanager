package com.decimelli.management.employeemanager.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.DepartmentManagement;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.repository.DepartmentManagementRepository;

@Service
public class ManagerService {

	@Autowired
	DepartmentManagementRepository repository;

	public List<DepartmentManagement> getManagedDepartmentHistory(Employee employee) {
		return repository.getManagedDepartment(employee.getId());
	}

	public List<DepartmentManagement> getDepartmentManagerHistory(Department department) {
		return repository.getDepartmentManager(department.getId());
	}

	public void makeAsManager(Employee employee, Date fromDate, Date toDate) {
		if(employee.getDepartmentHistory().isEmpty()) {
			return;
		}

		DepartmentManagement management = new DepartmentManagement();
		management.setDepartment(employee.getDepartmentHistory().get(0).getDepartment());
		management.setEmployee(employee);
		management.setFromDate(fromDate);
		management.setToDate(toDate);

		repository.save(management);
	}

	public void removeAsManager(Employee employee, Date lastDayAsManager) {
		for (DepartmentManagement management : getManagedDepartmentHistory(employee)) {
			if(management.getToDate().compareTo(lastDayAsManager) > 0) {
				management.setToDate(lastDayAsManager);
				repository.save(management);
			}
		}
	}

}
