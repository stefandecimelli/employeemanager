package com.decimelli.management.employeemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.decimelli.management.employeemanager.model.Department;
import com.decimelli.management.employeemanager.model.Employee;
import com.decimelli.management.employeemanager.repository.DepartmentRepository;
import com.decimelli.management.employeemanager.repository.EmployeeRepository;
import com.decimelli.management.employeemanager.repository.SalaryRepository;
import com.decimelli.management.employeemanager.service.DepartmentService;
import com.decimelli.management.employeemanager.service.EmployeeService;
import com.decimelli.management.employeemanager.service.ManagerService;

import jakarta.transaction.Transactional;

@SpringBootTest
class EmployeemanagerApplicationTests {

	@Autowired
	EmployeeRepository employees;

	@Autowired
	SalaryRepository salaries;

	@Autowired
	ManagerService managements;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentRepository departments;

	@Autowired
	DepartmentService departmentService;

	/**
	 * The full employee object should look something like this:
	 * 
	 * Employee(
	 * id=10192459,
	 * birthDate=1977-01-29,
	 * firstName=Stefan,
	 * lastName=Decimelli,
	 * gender=M,
	 * hireDate=1999-01-02,
	 * salaryHistory=[
	 * Salary(
	 * salary=50000,
	 * fromDate=2004-07-07,
	 * toDate=2024-08-29
	 * ),
	 * Salary(
	 * salary=40000,
	 * fromDate=1999-01-02,
	 * toDate=2004-07-07
	 * )
	 * ],
	 * titleHistory=[
	 * Title(
	 * title=Software Developer,
	 * fromDate=1999-01-02,
	 * toDate=2004-07-07
	 * ),
	 * Title(
	 * title=Software Development Manager,
	 * fromDate=2004-07-07,
	 * toDate=2024-08-29
	 * )
	 * ],
	 * departmentHistory=[
	 * (
	 * department=R&D,
	 * fromDate=2004-07-07,
	 * toDate=2024-08-29
	 * ),
	 * (
	 * department=Software Development,
	 * fromDate=1999-01-02,
	 * toDate=2004-07-07
	 * )
	 * ]
	 * )
	 * 
	 */
	@Transactional
	private Employee createEmployeeAndSimulate() throws Exception {
		Department department;
		Employee employee;

		// 1. Company created. Departments are made.

		departmentService.createDepartment("Software Development");
		departmentService.createDepartment("R&D");

		// 2. Employee is created

		employee = new Employee();
		employee.setBirthDate(Date.valueOf("1977-1-29"));
		employee.setFirstName("Stefan");
		employee.setLastName("Decimelli");
		employee.setGender('M');
		employee.setHireDate(Date.valueOf("1999-01-02"));
		employee.setId(10192459);
		employees.save(employee);

		// 3. Employee is assigned a job and a salary

		employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		department = departments.getDepartmentByName("Software Development").get(0);

		Date dateOfAssignment = Date.valueOf("1999-01-02");
		departmentService.assignEmployeeToDepartment(employee, department, dateOfAssignment);
		employeeService.setNewSalary(employee, 40000, dateOfAssignment);
		employeeService.setNewTitle(employee, "Software Developer", dateOfAssignment);

		// 4. Employee gets a promotion, becomes manager of another department

		employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		department = departments.getDepartmentByName("R&D").get(0);

		Date dateOfPromotion = Date.valueOf("2004-07-07");
		managements.makeAsManager(employee, department, dateOfPromotion);
		employeeService.setNewSalary(employee, 50000, dateOfPromotion);
		employeeService.setNewTitle(employee, "Software Development Manager", dateOfPromotion);

		assertEquals(
				managements.getDepartmentManagerHistory(department).isEmpty(),
				managements.getManagedDepartmentHistory(employee).isEmpty());

		// 5. Employee retires

		Date retirementDate = Date.valueOf("2024-08-29");
		employee = employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
		employeeService.retireEmployee(employee, retirementDate);

		// FINAL

		return employees.getEmployeeByName("Stefan", "Decimelli", null).get(0);
	}

	@Test
	public void testEmployeeLifecycle() throws Exception {
		Employee employee = createEmployeeAndSimulate();

		assertEquals(Date.valueOf("1977-1-29"), employee.getBirthDate());
		assertEquals("Stefan", employee.getFirstName());
		assertEquals("Decimelli", employee.getLastName());
		assertEquals(10192459, employee.getId());
		assertEquals(Date.valueOf("1999-01-02"), employee.getHireDate());
		assertEquals('M', employee.getGender());

		assertEquals(2, employee.getSalaryHistory().size());
		assertEquals(2, employee.getTitleHistory().size());
		assertEquals(2, employee.getDepartmentHistory().size());

		assertEquals(employee, employee.getSalaryHistory().get(0).getEmployee());
		assertEquals(50000, employee.getSalaryHistory().get(0).getSalary());
		assertEquals(employee, employee.getTitleHistory().get(0).getEmployee());
		assertEquals("Software Development Manager", employee.getTitleHistory().get(0).getTitle());
		assertEquals(employee, employee.getDepartmentHistory().get(0).getEmployee());
		assertEquals("R&D", employee.getDepartmentHistory().get(0).getDepartment().getName());

		assertEquals(employee, employee.getSalaryHistory().get(1).getEmployee());
		assertEquals(40000, employee.getSalaryHistory().get(1).getSalary());
		assertEquals(employee.getTitleHistory().get(1).getEmployee(), employee);
		assertEquals("Software Developer", employee.getTitleHistory().get(1).getTitle());
		assertEquals(employee, employee.getDepartmentHistory().get(1).getEmployee());
		assertEquals("Software Development", employee.getDepartmentHistory().get(1).getDepartment().getName());

		assertEquals(employee.getDepartmentHistory().get(0).getDepartment().getName(),
				managements.getManagedDepartmentHistory(employee).get(0).getDepartment().getName());
		assertEquals(employee.getDepartmentHistory().get(0).getDepartment().getId(),
				managements.getManagedDepartmentHistory(employee).get(0).getDepartment().getId());
	}

}
