package org.zkoss.jtjexample.model;

import org.zkoss.jtjexample.EmployeeEvent;
import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.jtjexample.service.EmployeeServiceProvider;
import org.zkoss.jtjexample.service.api.EmployeeService;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.AbstractListModel;

public class EmployeeModel extends AbstractListModel implements
		BindingListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3839512061918416900L;

	private EmployeeService _employeeService = EmployeeServiceProvider.INSTANCE
			.getEmployeeService();
	
	

	public EmployeeModel() {
		EventQueue eq = EventQueues.lookup("employee", EventQueues.APPLICATION, true);
		
		eq.subscribe(new EventListener() {

			public void onEvent(Event event) throws Exception {
				if(!(event instanceof EmployeeEvent)) {
					throw new IllegalArgumentException("The employee eventqueue must only contain EmployeeEvents");
				}
				
				EmployeeEvent empEvent = (EmployeeEvent)event;
				int index = indexOf(_employeeService.getEmployeeIndex(empEvent.getEmployee()));
				fireEvent(empEvent.getEventType(), index, index);
			}
			
		});
	}

	public Object getElementAt(int index) {
		return _employeeService.getEmployee(index);
	}

	public int getSize() {
		return _employeeService.getTotalEmployees();
	}

	public boolean add(Employee employee) {
		return _employeeService.addEmployee(employee);
	}
	
	public boolean update(Employee employee) {
		return _employeeService.updateEmployee(employee);
	}

	public boolean remove(Employee employee) {
		return _employeeService.removeEmployee(employee);
	}

	public int indexOf(Object obj) {
		int ret = -1;

		if ((obj instanceof Employee)) {
			ret = _employeeService.getEmployeeIndex((Employee) obj);
		}

		return ret;
	}

}
