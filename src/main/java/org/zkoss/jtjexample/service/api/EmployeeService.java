package org.zkoss.jtjexample.service.api;

import java.util.List;

import org.zkoss.jtjexample.bean.Employee;

public interface EmployeeService {
	Employee getEmployee(int index);
	List<Employee> getAllEmployees();
	int getEmployeeIndex(Employee employee);
	int getTotalEmployees();
	
	boolean addEmployee(String modelId, Employee employee);
	boolean updateEmployee(String modelId, Employee employee);
	boolean removeEmployee(String modelId, Employee employee);
}
