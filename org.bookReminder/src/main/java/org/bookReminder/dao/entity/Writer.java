package org.bookReminder.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "writer_document")
@Entity
@Table(name = "writer")
public class Writer implements Serializable {

	private static final long serialVersionUID = -82439648328404424L;

	@Id
	@org.springframework.data.annotation.Id
	@Column(name = "wrt_no")
	private Long wrtNo;

	@Column(name = "first_name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "wrt_book", joinColumns = { @JoinColumn(name = "wrt_no") }, inverseJoinColumns = {
			@JoinColumn(name = "book_no") })
	private List<Book> books;

	public Long getWrtNo() {
		return wrtNo;
	}

	public void setWrtNo(Long wrtNo) {
		this.wrtNo = wrtNo;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((wrtNo == null) ? 0 : wrtNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Writer other = (Writer) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (wrtNo == null) {
			if (other.wrtNo != null)
				return false;
		} else if (!wrtNo.equals(other.wrtNo))
			return false;
		return true;
	}
	
	

}
