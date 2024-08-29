package com.decimelli.management.employeemanager.repository;

import com.decimelli.management.employeemanager.model.DepartmentManagement;
import com.decimelli.management.employeemanager.model.DepartmentManagementId;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentManagementRepository extends
		ListCrudRepository<DepartmentManagement, DepartmentManagementId>,
		ListPagingAndSortingRepository<DepartmentManagement, DepartmentManagementId> {

	@Query(nativeQuery = true, value = """
				select
					*
				from
					dept_manager
				where
					emp_no = :employeeId
			""")
	List<DepartmentManagement> getManagedDepartment(@Param("employeeId") int employeeId);

	@Query(nativeQuery = true, value = """
				select
					*
				from
					dept_manager
				where
					dept_no = :departmentId
			""")
	List<DepartmentManagement> getDepartmentManager(@Param("departmentId") String departmentId);
}
