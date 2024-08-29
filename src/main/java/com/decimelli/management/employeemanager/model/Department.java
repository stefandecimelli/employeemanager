package com.decimelli.management.employeemanager.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = { "employeeHistory" })
@Entity
@Table(name = "departments")
public class Department {

	@Id
	@Getter
	@Setter
	@Column(name = "dept_no")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Getter
	@Setter
	@Column(name = "dept_name")
	private String name;

	@Getter
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<DepartmentAssignment> employeeHistory = new ArrayList<>();

}
