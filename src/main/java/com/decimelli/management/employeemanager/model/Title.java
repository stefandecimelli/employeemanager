package com.decimelli.management.employeemanager.model;

import java.sql.Date;

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

@ToString(exclude = { "employee" })
@Entity
@Table(name = "titles")
@IdClass(TitleId.class)
public class Title {

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

	@Getter
	@Setter
	@Column(name = "to_date")
	private Date toDate = Date.valueOf("9999-1-1");

}
