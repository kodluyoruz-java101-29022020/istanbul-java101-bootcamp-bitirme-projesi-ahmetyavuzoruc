package org.bookReminder.service.model;

import java.io.Serializable;


public class BookContext implements Serializable {

	private static final long serialVersionUID = 3906169278470348749L;
	

	private String name;
	private String type;
	private String authorName;
	
	
	public String getName() {
		return name;
	}
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


}
