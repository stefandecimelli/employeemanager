package com.decimelli.management.employeemanager.model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "dept_emp")
public class DepartmentAssignment {

	@EmbeddedId
	private DepartmentAssignmentId id = new DepartmentAssignmentId();;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("empNo")
	@JoinColumn(name = "emp_no")
	private Employee employee;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("deptNo")
	@JoinColumn(name = "dept_no")
	private Department department;

	@Getter
	@Setter
	@Column(name = "from_date", nullable = false)
	private Date fromDate;

	@Getter
	@Setter
	@Column(name = "to_date", nullable = false)
	private Date toDate;

	@Override
	public String toString() {
		return "DepartmentAssignment [department=" + department.getName() + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

}
