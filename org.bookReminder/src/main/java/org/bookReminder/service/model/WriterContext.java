package org.bookReminder.service.model;

import java.io.Serializable;

public class WriterContext implements Serializable {

	private static final long serialVersionUID = 3906169278470348749L;
	
	private String name;
	private String lastName;
	private String gender;

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

}
