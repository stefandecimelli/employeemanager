package com.decimelli.management.employeemanager.service;

import java.sql.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decimelli.management.employeemanager.model.DepartmentAssignment;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.model.Salary;
import com.decimelli.management.employeemanager.model.Title;
import com.decimelli.management.employeemanager.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	ManagerService management;

	@Autowired
	EmployeeRepository employees;

	public void retireEmployee(Employee employee, Date retirementDate) {
		for (Salary salary : employee.getSalaryHistory()) {
			if (salary.getToDate().compareTo(retirementDate) > 0) {
				salary.setToDate(retirementDate);
			}
		}
		for (Title title : employee.getTitleHistory()) {
			if (title.getToDate().compareTo(retirementDate) > 0) {
				title.setToDate(retirementDate);
			}
		}
		for (DepartmentAssignment departmentAssignment : employee.getDepartmentHistory()) {
			if (departmentAssignment.getToDate().compareTo(retirementDate) > 0) {
				departmentAssignment.setToDate(retirementDate);
			}
		}
		management.removeAsManager(employee, retirementDate);
		employees.save(employee);
	}

	public void setNewSalary(Employee employee, int newSalaryValue, Date from, Date to) {
		if (employee.getSalaryHistory() == null) {
			employee.setSalaryHistory(new HashSet<>());
		}
		Salary newSalary = new Salary();
		newSalary.setFromDate(from);
		newSalary.setToDate(to);
		newSalary.setSalary(newSalaryValue);
		newSalary.setEmployee(employee);

		for (Salary oldSalary : employee.getSalaryHistory()) {
			if (oldSalary.getToDate().compareTo(from) > 0) {
				oldSalary.setToDate(from);
			}
		}

		employee.getSalaryHistory().add(newSalary);
		employees.save(employee);
	}

	public void setNewTitle(Employee employee, String newTitleName, Date from, Date to) {
		if (employee.getTitleHistory() == null) {
			employee.setTitleHistory(new HashSet<>());
		}
		Title newSalary = new Title();
		newSalary.setFromDate(from);
		newSalary.setToDate(to);
		newSalary.setTitle(newTitleName);
		newSalary.setEmployee(employee);

		for (Title oldTitle : employee.getTitleHistory()) {
			if (oldTitle.getToDate().compareTo(from) > 0) {
				oldTitle.setToDate(from);
			}
		}

		employee.getTitleHistory().add(newSalary);
		employees.save(employee);
	}

}
