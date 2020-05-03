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

@Document(collection = "book_document")
@Entity
@Table(name = "book")
public class Book implements Serializable {

	private static final long serialVersionUID = -82439648328404424L;

	@Id
	@org.springframework.data.annotation.Id
	@Column(name = "book_no")
	private Long bookNo;

	@Column(name = "book_name")
	private String name;

	@Column(name = "book_type")
	private String type;

	//@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "author_book", 
			joinColumns = { @JoinColumn(name = "book_no") }, 
			inverseJoinColumns = { @JoinColumn(name = "author_no") })
	
	private List<Author> author;

	public Long getBookNo() {
		return bookNo;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public List<Author> getAuthor() {
		return author;
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

	public void setAuthor(List<Author> author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Book other = (Book) obj;
		if (bookNo == null) {
			if (other.bookNo != null)
				return false;
		} else if (!bookNo.equals(other.bookNo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
	

}
