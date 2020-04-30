package org.bookReminder.service.model;

import org.bookReminder.dao.entity.Writer;

public class WriterProfile {

	private Writer writer;
	private String bookName;
	
	public WriterProfile() { }
	
	public WriterProfile(Writer writer, String bookName) {
		this.writer = writer;
		this.bookName = bookName;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getBookName() {
		return bookName;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
}
