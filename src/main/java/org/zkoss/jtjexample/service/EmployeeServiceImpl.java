package org.zkoss.jtjexample.service;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.jtjexample.service.api.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	
	private List<Employee> _employees = new ArrayList<Employee>();
	
	public EmployeeServiceImpl() {
		_employees.add(new Employee("Harold", "Rogers", 24));
		_employees.add(new Employee("Roger", "Knight", 20));
		_employees.add(new Employee("David", "Collins", 30));
	}
	
	public Employee getEmployee(int index) {
		
		if(index >= _employees.size()) {
			throw new IllegalArgumentException("Index cannot be larger than the employee numbers");
		}
		
		return _employees.get(index);
	}

	public synchronized List<Employee> getAllEmployees() {
		return _employees;
	}

	public synchronized boolean addEmployee(Employee employee) {
		
		if(employee == null) {
			throw new NullPointerException("employee cannot be null");
		}
		
		return _employees.add(employee);		
	}

	public synchronized boolean updateEmployee(Employee employee) {
		
		if(employee == null) {
			throw new NullPointerException("employee cannot be null");
		}
		
		boolean ret = false;
		
		int index = _employees.indexOf(employee);
		
		if(index > -1) {
			Employee emp = _employees.get(index);
			emp.setFirstName(emp.getFirstName());
			emp.setLastName(emp.getLastName());
			emp.setAge(emp.getAge());
			
			ret = true;
		}
		
		return ret;
	}

	public synchronized boolean removeEmployee(Employee employee) {
		
		if(employee == null) {
			throw new NullPointerException("employee cannot be null");
		}
		
		return _employees.remove(employee);
	}

	public int getTotalEmployees() {
		return _employees.size();
	}

	public int getEmployeeIndex(Employee employee) {
		return _employees.indexOf(employee);
	}

}
