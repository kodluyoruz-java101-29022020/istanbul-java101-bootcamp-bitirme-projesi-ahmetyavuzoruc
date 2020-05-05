package org.bookReminder.dao.jpa.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.bookReminder.dao.jpa.entity.Book;
import org.bookReminder.service.model.BookProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {



		@Query("SELECT book FROM Book b WHERE b.bookNo=:bookNo")
		public Book findByBookNo(@Param("bookNo") Long bookNo);

		@Query(value = "SELECT b FROM Book b")
		public List<Book> getAllBookList();
		
		@Query(value = "SELECT MAX(b.bookNo) FROM Book b")
		public Long findMaxBookNo();

		@Query(value = "SELECT b FROM Book b WHERE b.bookNo=:bookNo")
		public Book findWithBookNo(@Param("bookNo") Long bookNo);
		
		
		@Query(value = "SELECT b FROM Book b ORDER BY b.bookName ")
		public List<Book> findAllByOrderByBookNameAsc();
		
		@Query(value = "SELECT org.bookReminder.service.model.BookProfile(book, authorName) FROM Book book LEFT OUTER JOIN book authorName")
		public List<BookProfile> getAllEmployeeProfileList(Pageable pageable);

		
		
}
