package org.zkoss.jtjexample.controller;

import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.jtjexample.service.EmployeeService;
import org.zkoss.jtjexample.utils.Messages;
import org.zkoss.jtjexample.utils.UiUtils;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class EmployeeController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9089818718875587898L;

	private static final Log log = Log.lookup(EmployeeController.class);

	private Employee _currentEmployee;
	private ListModelList _model = EmployeeService.INSTANCE.getModel();

	Listbox lstEmployee;
	Textbox txtFirstName, txtLastName;
	Intbox intAge;

	public Employee getCurrentEmployee() {
		return _currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {		
		this._currentEmployee = currentEmployee;
	}

	public ListModel getEmployeeModel() {
		return _model;
	}

	// click events
	public void onClick$btnAddEmployee(ForwardEvent fe) {

		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		int iAge = Integer.parseInt(intAge.getText());

		Employee employee = new Employee(firstName, lastName, iAge);

		if (!_model.add(employee)) {
			reportError(Messages.getString("EmployeeController.0"), employee);
		}

	}

	public void onClick$btnUpdateEmployee(ForwardEvent fe) {
		if (lstEmployee.getSelectedItem() != null) {

			Employee employee = (Employee) (lstEmployee.getSelectedItem()
					.getValue());
			
			employee.setFirstName(txtFirstName.getText());
			employee.setLastName(txtLastName.getText());
			employee.setAge(Integer.parseInt(intAge.getText()));
		} else {
			UiUtils.showMessage(Messages.getString("EmployeeController.2"));
		}
	}

	public void onClick$btnDeleteEmployee(ForwardEvent fe) {

		if (lstEmployee.getSelectedItem() != null) {
			Employee employee = (Employee) (lstEmployee.getSelectedItem()
					.getValue());

			if (!_model.remove(employee)) {
				reportError(Messages.getString("EmployeeController.3"), employee);
			}

		} else {
			UiUtils.showMessage(Messages.getString("EmployeeController.4"));
		}

	}

	private void reportError(String message, Employee employee) {
		StringBuilder sb = new StringBuilder(message).append(Messages.getString("EmployeeController.5")).append(employee);
		final String error = sb.toString();

		UiUtils.showMessage(error);
		log.error(error);
	}

}
