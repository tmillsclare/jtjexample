package org.zkoss.jtjexample.model;

import java.util.UUID;

import org.zkoss.jtjexample.EmployeeEvent;
import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.jtjexample.service.EmployeeServiceProvider;
import org.zkoss.jtjexample.service.api.EmployeeService;
import org.zkoss.jtjexample.utils.Messages;
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
	
	private final String _myModelId = UUID.randomUUID().toString();
	

	public EmployeeModel() {
		EventQueue eq = EventQueues.lookup(Messages.getString("EmployeeModel.0"), EventQueues.APPLICATION, true); //$NON-NLS-1$
		
		eq.subscribe(new EventListener() {

			public void onEvent(Event event) throws Exception {
				if(!(event instanceof EmployeeEvent)) {
					throw new IllegalArgumentException(Messages.getString("EmployeeModel.1")); //$NON-NLS-1$
				}
				
				EmployeeEvent empEvent = (EmployeeEvent)event;
				
				if(!_myModelId.equals(empEvent.getModelId())) {
					fireEvent(empEvent.getEventType(), empEvent.getIndexStart(), empEvent.getIndexEnd());
				}
			}
			
		});
	}
	
	public String getModelId() {
		return _myModelId;
	}

	public Object getElementAt(int index) {
		return _employeeService.getEmployee(index);
	}

	public int getSize() {
		return _employeeService.getTotalEmployees();
	}

	public boolean add(Employee employee) {
		return _employeeService.addEmployee(_myModelId, employee);
	}
	
	public boolean update(Employee employee) {
		return _employeeService.updateEmployee(_myModelId, employee);
	}

	public boolean remove(Employee employee) {
		return _employeeService.removeEmployee(_myModelId, employee);
	}

	public int indexOf(Object obj) {
		int ret = -1;

		if ((obj instanceof Employee)) {
			ret = _employeeService.getEmployeeIndex((Employee) obj);
		}

		return ret;
	}

}
