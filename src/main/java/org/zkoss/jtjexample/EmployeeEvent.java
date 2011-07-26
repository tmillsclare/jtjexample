package org.zkoss.jtjexample;

import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.zk.ui.event.Event;

public class EmployeeEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3357970626839071425L;
	
	private static final String eventName = "employeeEvent";
	
	private Employee _employee;
	private int _eventType;
	
	public EmployeeEvent(Employee employee, int eventType) {
		super(eventName);
	}

	public Employee getEmployee() {
		return _employee;
	}

	public void setEmployee(Employee employee) {
		this._employee = employee;
	}

	public int getEventType() {
		return _eventType;
	}

	public void setEventType(int eventType) {
		this._eventType = eventType;
	}
	
	
}
