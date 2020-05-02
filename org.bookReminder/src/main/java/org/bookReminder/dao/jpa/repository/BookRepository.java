package org.bookReminder.dao.jpa.repository;

import java.util.List;

import org.bookReminder.dao.entity.Book;
import org.bookReminder.service.model.BookProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {


	
	@Query(value = "FROM Book e WHERE e.bookNo = :bookNo")
	public Book findWithBookNo(@Param("bookNo") Long bookNo);
	
	@Query(value = "SELECT MAX(e.bookNo) FROM Book e")
	public Long findMaxId();

	@Query(value = "SELECT e FROM Book e")
	public List<Book> getAllBookList();
	
	@Query(value = "SELECT new org.bookReminder.service.model.BookProfile(book, bookAuthor.name) FROM Book book LEFT OUTER JOIN book.author bookAuthor")
	public List<BookProfile> getAllBookProfileList(Pageable pageable);
	
}
