package com.decimelli.management.employeemanager.model;

import java.sql.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(exclude = { "employee" })
@IdClass(SalaryId.class)
@Table(name = "salaries")
public class Salary {

	@Id
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "emp_no")
	private Employee employee;

	@Getter
	@Setter
	@Column(name = "salary")
	private Integer salary;

	@Id
	@Getter
	@Setter
	@Column(name = "from_date")
	private Date fromDate;

	@Getter
	@Setter
	@Nullable
	@Column(name = "to_date")
	private Date toDate = Date.valueOf("9999-1-1");

}
