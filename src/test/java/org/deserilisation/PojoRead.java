package org.deserilisation;

import java.util.ArrayList;

public class PojoRead {
	
	private String name;
	
	private int age;
	
	private boolean status;
	
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getAge() {
		return age;
	}

	protected void setAge(int age) {
		this.age = age;
	}

	protected boolean isStatus() {
		return status;
	}

	protected void setStatus(boolean status) {
		this.status = status;
	}

	protected EmployeeAddress getEmployeeAddress() {
		return employeeAddress;
	}

	protected void setEmployeeAddress(EmployeeAddress employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	protected ArrayList<String> getCourses() {
		return courses;
	}

	protected void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

	private EmployeeAddress employeeAddress;
	
	private ArrayList<String> courses;


}
