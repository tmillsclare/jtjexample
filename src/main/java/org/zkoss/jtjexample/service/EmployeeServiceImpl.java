package org.zkoss.jtjexample.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListDataEvent;

import org.zkoss.jtjexample.EmployeeEvent;
import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.jtjexample.service.api.EmployeeService;
import org.zkoss.jtjexample.utils.Messages;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;

public class EmployeeServiceImpl implements EmployeeService {
	
	private final List<Employee> _employees = new ArrayList<Employee>();
	private final EventQueue eq = EventQueues.lookup(Messages.getString("EmployeeServiceImpl.0"), EventQueues.APPLICATION, true);  
	
	public EmployeeServiceImpl() {
		_employees.add(new Employee(Messages.getString("EmployeeServiceImpl.1"), Messages.getString("EmployeeServiceImpl.2"), 24));   //$NON-NLS-2$
		_employees.add(new Employee(Messages.getString("EmployeeServiceImpl.3"), Messages.getString("EmployeeServiceImpl.4"), 20));   //$NON-NLS-2$
		_employees.add(new Employee(Messages.getString("EmployeeServiceImpl.5"), Messages.getString("EmployeeServiceImpl.6"), 30));   //$NON-NLS-2$
	}
	
	public Employee getEmployee(int index) {
		
		if(index >= _employees.size()) {
			throw new IllegalArgumentException(Messages.getString("EmployeeServiceImpl.7"));  
		}
		
		return _employees.get(index);
	}

	public synchronized List<Employee> getAllEmployees() {
		return _employees;
	}

	public synchronized boolean addEmployee(String modelId, Employee employee) {
		
		if(employee == null) {
			throw new NullPointerException(Messages.getString("EmployeeServiceImpl.8"));  
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
			throw new NullPointerException(Messages.getString("EmployeeServiceImpl.8"));  
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
			throw new NullPointerException(Messages.getString("EmployeeServiceImpl.8"));  
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
