package com.decimelli.management.employeemanager.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.decimelli.management.employeemanager.model.Employee;

public interface EmployeeRepository extends
		ListCrudRepository<Employee, Integer>,
		ListPagingAndSortingRepository<Employee, Integer> {

	@Query(nativeQuery = true, value = """
			select
				*
			from
				employees e
			where
				(:firstName IS NULL OR :firstName = '' OR e.first_name = :firstName)
			and
				(:lastName IS NULL OR :lastName = '' OR e.last_name = :lastName)
			""")
	public List<Employee> getEmployeeByName(
			@Param("firstName") @Nullable String firstName,
			@Param("lastName") @Nullable String lastName,
			Pageable pageRequest);

	@Query(nativeQuery = true, value = """
			select
				hire_date
			from
				employees e
			where
				(:firstName IS NULL OR :firstName = '' OR e.first_name = :firstName)
			and
				(:lastName IS NULL OR :lastName = '' OR e.last_name = :lastName)
			limit
				1
			""")
	public List<Date> getEmployeeHireData(
		@Param("firstName") @Nullable String firstName,
		@Param("lastName") @Nullable String lastName,
		Pageable pageRequest);
}
