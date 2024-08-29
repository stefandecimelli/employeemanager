package com.decimelli.management.employeemanager.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Table(name = "employees")
public class Employee {

	@Id
	@Getter
	@Setter
	@Column(name = "emp_no")
	private Integer id;

	@Getter
	@Setter
	@Column(name = "birth_date")
	private Date birthDate;

	@Getter
	@Setter
	@Column(name = "first_name")
	private String firstName;

	@Getter
	@Setter
	@Column(name = "last_name")
	private String lastName;

	@Getter
	@Setter
	@Column(name = "gender")
	private Character gender;

	@Getter
	@Setter
	@Column(name = "hire_date")
	private Date hireDate;

	@Getter
	@Setter
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Salary> salaryHistory;

	@Getter
	@Setter
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Title> titleHistory;

	public void addTitle(Title newTitle) {
		if (newTitle != null) {
			if (titleHistory == null) {
				titleHistory = new HashSet<>();
			}

			for (Title oldTitle : titleHistory) {
				if (oldTitle.getToDate().compareTo(newTitle.getFromDate()) > 0) {
					oldTitle.setToDate(newTitle.getFromDate());
				}
			}

			newTitle.setEmployee(this);
			titleHistory.add(newTitle);
		}
	}

	@Getter
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DepartmentAssignment> departmentHistory = new ArrayList<>();

}
