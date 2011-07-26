package org.zkoss.jtjexample.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListDataEvent;

import org.zkoss.jtjexample.EmployeeEvent;
import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.jtjexample.service.api.EmployeeService;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;

public class EmployeeServiceImpl implements EmployeeService {
	
	private final List<Employee> _employees = new ArrayList<Employee>();
	private final EventQueue eq = EventQueues.lookup("employee", EventQueues.APPLICATION, true);
	
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

	public synchronized boolean addEmployee(String modelId, Employee employee) {
		
		if(employee == null) {
			throw new NullPointerException("employee cannot be null");
		}
		
		int index = _employees.size();
		boolean ret = _employees.add(employee);
		
		if(ret) {
			EmployeeEvent employeeEvent = new EmployeeEvent(modelId, ListDataEvent.INTERVAL_ADDED, index, index);
			eq.publish(employeeEvent);
		}
		
		return ret;
	}

	public synchronized boolean updateEmployee(String modelId, Employee employee) {
		
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
			
			EmployeeEvent employeeEvent = new EmployeeEvent(modelId, ListDataEvent.CONTENTS_CHANGED, index, index);
			eq.publish(employeeEvent);
		}
		
		return ret;
	}

	public synchronized boolean removeEmployee(String modelId, Employee employee) {
		
		if(employee == null) {
			throw new NullPointerException("employee cannot be null");
		}
		
		int index = _employees.indexOf(employee);
		boolean ret = _employees.remove(employee);
		
		if(ret) {
			EmployeeEvent employeeEvent = new EmployeeEvent(modelId, ListDataEvent.INTERVAL_REMOVED, index, index);
			eq.publish(employeeEvent);
		}
		
		return ret;
	}

	public int getTotalEmployees() {
		return _employees.size();
	}

	public int getEmployeeIndex(Employee employee) {
		return _employees.indexOf(employee);
	}

}
