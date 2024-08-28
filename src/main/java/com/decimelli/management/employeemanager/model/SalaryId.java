package com.decimelli.management.employeemanager.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
public class SalaryId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	@ManyToOne
	@JoinColumn(name = "emp_no")
	private Employee employee;

	@Setter
	@Getter
	private Date fromDate;

}