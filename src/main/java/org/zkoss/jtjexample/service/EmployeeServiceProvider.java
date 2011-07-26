package org.zkoss.jtjexample.service;

import org.zkoss.jtjexample.service.api.EmployeeService;

public enum EmployeeServiceProvider {
	INSTANCE;
	
	final EmployeeService _employeeService;
	
	private EmployeeServiceProvider() {
		_employeeService = new EmployeeServiceImpl();
	}
	
	public EmployeeService getEmployeeService() {
		return _employeeService;
	}
}
