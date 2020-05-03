package org.bookReminder.dao.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "author")
public class Author {

	@Id
	@Column(name = "author_no", columnDefinition = "varchar(255)")
	private String authorNo;
	
	@Column(name = "author_name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "author", fetch = FetchType.LAZY)
	private List<Book> book;

	public String getAuthorNo() {
		return authorNo;
	}

	public String getName() {
		return name;
	}

	public List<Book> getBook() {
		return book;
	}

	public void setAuthorNo(String authorNo) {
		this.authorNo = authorNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}
	

	
}
