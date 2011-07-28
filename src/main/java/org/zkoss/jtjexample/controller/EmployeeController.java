package org.zkoss.jtjexample.controller;

import org.zkoss.jtjexample.bean.Employee;
import org.zkoss.jtjexample.model.EmployeeModel;
import org.zkoss.jtjexample.utils.Messages;
import org.zkoss.jtjexample.utils.UiUtils;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class EmployeeController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9089818718875587898L;

	private static final Log log = Log.lookup(EmployeeController.class);

	private Employee _currentEmployee;
	private EmployeeModel _model = new EmployeeModel();

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
	public void onClick$btnAddEmployee() {

		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		int iAge = Integer.parseInt(intAge.getText());

		Employee employee = new Employee(firstName, lastName, iAge);

		if (!_model.add(employee)) {
			reportError(Messages.getString("EmployeeController.0"), employee); //$NON-NLS-1$
		}

	}

	public void onClick$btnUpdateEmployee() {
		if (lstEmployee.getSelectedItem() != null) {

			Employee employee = (Employee) (lstEmployee.getSelectedItem()
					.getValue());
			
			employee.setFirstName(txtFirstName.getText());
			employee.setLastName(txtLastName.getText());
			employee.setAge(Integer.parseInt(intAge.getText()));

			if (!_model.update(employee)) {
				reportError(Messages.getString("EmployeeController.1"), employee); //$NON-NLS-1$
			}
		} else {
			UiUtils.showMessage(Messages.getString("EmployeeController.2")); //$NON-NLS-1$
		}
	}

	public void onClick$btnDeleteEmployee() {

		if (lstEmployee.getSelectedItem() != null) {
			Employee employee = (Employee) (lstEmployee.getSelectedItem()
					.getValue());

			if (!_model.remove(employee)) {
				reportError(Messages.getString("EmployeeController.3"), employee); //$NON-NLS-1$
			}

		} else {
			UiUtils.showMessage(Messages.getString("EmployeeController.4")); //$NON-NLS-1$
		}

	}

	private void reportError(String message, Employee employee) {
		StringBuilder sb = new StringBuilder(message).append(Messages.getString("EmployeeController.5")).append( //$NON-NLS-1$
				employee);
		final String error = sb.toString();

		UiUtils.showMessage(error);
		log.error(error);
	}

}
