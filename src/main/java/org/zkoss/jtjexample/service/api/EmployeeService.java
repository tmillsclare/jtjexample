package org.zkoss.jtjexample.service.api;

import java.util.List;

import org.zkoss.jtjexample.bean.Employee;

public interface EmployeeService {
	Employee getEmployee(int index);
	List<Employee> getAllEmployees();
	int getEmployeeIndex(Employee employee);
	int getTotalEmployees();
	
	boolean addEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	boolean removeEmployee(Employee employee);
}
