package com.decimelli.management.employeemanager.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.DepartmentAssignment;
import com.decimelli.management.employeemanager.model.DepartmentManagement;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.model.Salary;
import com.decimelli.management.employeemanager.model.Title;
import com.decimelli.management.employeemanager.repository.EmployeeRepository;

@Service
public class RetirementService {

	@Autowired
	ManagerService management;
	
	@Autowired
	EmployeeRepository employees;

	public void retireEmployee(Employee employee, Date retirementDate) {
		for(Salary salary : employee.getSalaryHistory()) {
			if(salary.getToDate().compareTo(retirementDate) > 0) {
				salary.setToDate(retirementDate);
			}
		}
		for(Title title : employee.getTitleHistory()) {
			if(title.getToDate().compareTo(retirementDate) > 0) {
				title.setToDate(retirementDate);
			}
		}
		for(DepartmentAssignment departmentAssignment : employee.getDepartmentHistory()) {
			if(departmentAssignment.getToDate().compareTo(retirementDate) > 0) {
				departmentAssignment.setToDate(retirementDate);
			}
		}
		management.removeAsManager(employee, retirementDate);
		employees.save(employee);
	}

}
