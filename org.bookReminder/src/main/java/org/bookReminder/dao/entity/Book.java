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
@Table(name = "books")
public class Book {

	@Id
	@Column(name = "book_no", columnDefinition = "varchar(255)")
	private String bookNo;
	
	@Column(name = "book_name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	private List<Writer> writer;
	

	public List<Writer> getWriter() {
		return writer;
	}

	public void setWriters(List<Writer> writer) {
		this.writer = writer;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
