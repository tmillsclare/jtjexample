package org.zkoss.jtjexample.bean;

import java.util.UUID;

public class Employee {
	private String _uuid, _firstName, _lastName;
	private int _age;
	
	public Employee(String firstName, String lastName, int age) {
		_uuid = UUID.randomUUID().toString();
		_firstName = firstName;
		_lastName = lastName;
		_age = age;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		this._uuid = uuid;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	public int getAge() {
		return _age;
	}

	public void setAge(int age) {
		this._age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Employee)) return false;
		
		Employee employee = (Employee)obj;
		return this._uuid.equals(employee.getUuid());
	}

	@Override
	public int hashCode() {
		return _uuid.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Employee {uuid = ");
		sb.append(getUuid());
		sb.append(", firstName = ");
		sb.append(getFirstName());
		sb.append(", lastName = ");
		sb.append(getLastName());
		sb.append(", age = ");
		sb.append(getAge());
		sb.append("}");
		
		return sb.toString();
	}
}
