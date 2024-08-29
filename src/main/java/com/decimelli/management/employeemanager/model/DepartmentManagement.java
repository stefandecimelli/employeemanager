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
@Table(name = "dept_manager")
@ToString(exclude = { "id" })
public class DepartmentManagement {

	@Getter
	@EmbeddedId
	private DepartmentManagementId id;

	@Getter
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("deptNo")
	@JoinColumn(name = "dept_no", nullable = false)
	private Department department;

	public void setDepartment(Department department) {
		this.department = department;

		if (this.id == null) {
			this.id = new DepartmentManagementId();
		}
		this.id.setDeptNo(department.getId());
	}

	@Getter
	@Setter
	@Column(name = "from_date")
	private Date fromDate;

	@Getter
	@Setter
	@Column(name = "to_date")
	private Date toDate;

	@Getter
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("empNo")
	@JoinColumn(name = "emp_no", nullable = false)
	private Employee employee;

	public void setEmployee(Employee employee) {
		this.employee = employee;

		if (this.id == null) {
			this.id = new DepartmentManagementId();
		}
		this.id.setEmpNo(employee.getId());
	}

}
