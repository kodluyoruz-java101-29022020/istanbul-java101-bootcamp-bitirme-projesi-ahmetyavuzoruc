package org.bookReminder.dao.mongo.entity;

import java.io.Serializable;
import java.math.BigInteger;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "writer_document")
public class WriterDraft implements Serializable{


	private static final long serialVersionUID = -1450263362382412146L;

	@org.springframework.data.annotation.Id
	private BigInteger wrtNo;
	
	private String name;
	
	private String lastName;

	public BigInteger getWrtNo() {
		return wrtNo;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setWrtNo(BigInteger wrtNo) {
		this.wrtNo = wrtNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
}
