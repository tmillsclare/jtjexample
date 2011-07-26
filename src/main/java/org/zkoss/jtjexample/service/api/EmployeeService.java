package org.zkoss.jtjexample.service.api;

import java.util.List;

import org.zkoss.jtjexample.bean.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void removeEmployee(Employee employee);
}
