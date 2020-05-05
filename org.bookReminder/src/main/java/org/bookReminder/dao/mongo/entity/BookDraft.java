package org.bookReminder.dao.mongo.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_document")
public class BookDraft implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long bookNo;
	
	private String name;
	
	private String type;
	
	private String authorName;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Long getBookNo() {
		return bookNo;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
