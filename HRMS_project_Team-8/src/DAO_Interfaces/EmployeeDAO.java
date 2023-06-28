package DAO_Interfaces;

import java.util.List;

import javax.servlet.http.HttpSession;

import models.Employee;
import models.EmployeeParameter;

public interface EmployeeDAO {

	/**
	 * Retrieves a list of all employees.
	 *
	 * @return A list of all employees.
	 */
	List<Employee> getAllEmployees(HttpSession session);

	/**
	 * Retrieves an employee by their ID.
	 *
	 * @param id The ID of the employee.
	 * @return The employee with the specified ID.
	 */
	Employee getEmployeeById(int id);

	/**
	 * Inserts an employee into the database.
	 *
	 * @param employee The employee to be inserted.
	 */
	void insertEmployee(Employee employee);

	/**
	 * Updates the status of an employee.
	 *
	 * @param id        The ID of the employee.
	 * @param newStatus The new status value to be set.
	 */
	void updateEmployeeStatus(int id, String newStatus);

	/**
	 * Updates the details of an employee.
	 *
	 * @param employee The employee with updated details.
	 */
	void updateEmployee(Employee employee);

	/**
	 * Retrieves a list of employee parameters by employee ID.
	 *
	 * @param employeeId The ID of the employee.
	 * @return A list of employee parameters associated with the employee.
	 */
	List<EmployeeParameter> getEmployeeParametersById(Integer employeeId);

	/**
	 * Retrieves a list of employees grouped by HR and Manager.
	 *
	 * @param employeeId The ID of the employee.
	 * @return A list of employees grouped by HR and Manager.
	 */
	List<Employee> getEmployeesByHRAndManager(int employeeId);

	/**
	 * Retrieves an employee by their ID.
	 *
	 * @param employeeId The ID of the employee.
	 * @return The employee with the specified ID.
	 */
	Employee getEmployee(int employeeId);
}
