package org.bookReminder.dao.mongo.entity;

import java.io.Serializable;
import java.math.BigInteger;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_document")
public class BookDraft implements Serializable{


	private static final long serialVersionUID = -1450263362382412146L;

	@org.springframework.data.annotation.Id
	private BigInteger bookNo;
	
	private String name;
	
	private String type;

	public BigInteger getBookNo() {
		return bookNo;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setBookNo(BigInteger bookNo) {
		this.bookNo = bookNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
