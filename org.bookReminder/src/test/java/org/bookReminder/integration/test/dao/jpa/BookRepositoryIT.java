package org.bookReminder.integration.test.dao.jpa;


import java.util.List;

import org.bookReminder.dao.jpa.entity.Book;
import org.bookReminder.dao.jpa.repository.BookRepository;
import org.bookReminder.service.model.BookProfile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource({ "classpath:application.properties" })
public class BookRepositoryIT {

	@Autowired
	private BookRepository bookRepository;
	
	
	@Test
	@Order(1)
	public void selectBookByBookNo() {
		
		Long maxId = bookRepository.findMaxBookNo();
		Book book = bookRepository.findWithBookNo(maxId);
		
		Assert.assertNotNull(book);
		Assert.assertTrue(book.getBookNo() > 0);
	}
	
	
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(3)
	public void saveBook() {
		
		Long maxId = bookRepository.findMaxBookNo();
		Long newBookId = maxId + 1;
		
		Book book = new Book();
		book.setBookNo(newBookId);
		book.setName("Sefiller");
		book.setType("Dram");
		
		
		
		bookRepository.save(book);
		
		book = bookRepository.findWithBookNo(newBookId);
		
		Assert.assertNotNull(book);
		Assert.assertTrue(book.getBookNo() > 0);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Order(4)
	public void updateBook() {
		
		Long maxId = bookRepository.findMaxBookNo();
		Book book = bookRepository.findWithBookNo(maxId);
		
		book.setName("Cimri");
		book.setType("Polisiye");
		
		
		bookRepository.save(book);
		
		book = bookRepository.findWithBookNo(maxId);
		
		Assert.assertEquals("Cimri", book.getName());
		Assert.assertEquals("Polisiye", book.getType());
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void deleteBook() {
		
		Long maxId = bookRepository.findMaxBookNo();
		Book book = bookRepository.findWithBookNo(maxId);
		
		bookRepository.delete(book);
		
		book = bookRepository.findWithBookNo(maxId);
		
		Assert.assertNull(book);
	}
}
