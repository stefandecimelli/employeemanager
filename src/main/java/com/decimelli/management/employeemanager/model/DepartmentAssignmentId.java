package com.decimelli.management.employeemanager.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
public class DepartmentAssignmentId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
    @Column(name = "emp_no")
    private Integer empNo;

	@Setter
	@Getter
    @Column(name = "dept_name", length = 4)
    private String deptNo;

}
