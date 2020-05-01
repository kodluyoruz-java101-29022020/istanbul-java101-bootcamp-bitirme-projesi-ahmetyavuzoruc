package org.bookReminder.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@Column(name = "author_no", columnDefinition = "varchar(255)")
	private String authorNo;
	
	@Column(name = "author_name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
	private List<Book> book;
	

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	public String getBookNo() {
		return authorNo;
	}

	public void setBookNo(String authorNo) {
		this.authorNo = authorNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
