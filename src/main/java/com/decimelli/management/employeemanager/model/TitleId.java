package com.decimelli.management.employeemanager.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class TitleId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "emp_no")
	private Employee employee;

	@Id
	@Getter
	@Setter
	private String title;

	@Id
	@Getter
	@Setter
	@Column(name = "from_date")
	private Date fromDate;
}
