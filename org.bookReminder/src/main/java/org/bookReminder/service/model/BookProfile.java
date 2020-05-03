package org.bookReminder.service.model;

import org.bookReminder.dao.jpa.entity.Book;

public class BookProfile {

	private Book book;
	private String authorName;
	
	public BookProfile() { }
	
	public BookProfile(Book book, String authorName) {
		this.book = book;
		this.authorName = authorName;
	}

	public Book getBook() {
		return book;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	
	
}
