package com.decimelli.management.employeemanager.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
public class DepartmentManagementId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(name = "emp_no")
	private Integer empNo;

	@Getter
	@Setter
	@Column(name = "dept_no")
    private String deptNo;
}
