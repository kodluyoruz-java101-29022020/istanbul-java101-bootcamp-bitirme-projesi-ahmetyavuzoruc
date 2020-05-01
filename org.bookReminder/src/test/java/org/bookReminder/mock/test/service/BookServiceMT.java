package org.bookReminder.mock.test.service;

import java.util.Arrays;
import java.util.List;

import org.bookReminder.dao.entity.Book;
import org.bookReminder.dao.jpa.repository.BookRepository;
import org.bookReminder.dao.mongo.entity.BookDraft;
import org.bookReminder.dao.mongo.entity.repository.BookDraftRepository;
import org.bookReminder.service.BookService;
import org.bookReminder.service.model.BookContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceMT {

	@Mock
    private BookRepository bookRepository;
	
	@Mock
	private BookDraftRepository bookDraftRepository;
	
	@InjectMocks
	private BookService bookService;
	
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void queryBook() {
		
		Book book = new Book();
		book.setBookNo(10L);
		book.setName("Cimri");
		book.setType("Polisiye");
	  
	    
		Book book3 = new Book();
		book3.setBookNo(11L);
		book3.setName("Sefiller");
		book3.setType("Dram");
		
	   
		 
	    Mockito
	    	.when(bookRepository.findWithBookNo(10L))
	    	.thenReturn(book);
	    
	    Mockito
	    	.when(bookRepository.findWithBookNo(11L))
	    	.thenReturn(book3);
	    
		
		Book book2 = bookService.findByBookNo(11L);
		
		Assert.assertNotNull(book2);
		Assert.assertTrue(book2.getBookNo() > 0);
	}
	
	@Test
	public void queryAllBooks() {
		
		Book book1 = new Book();
		book1.setBookNo(10L);
		book1.setName("Cimri");
		book1.setType("Polisiye");
	   
	    
	    Book book2 = new Book();
	    book2.setBookNo(10L);
	    book2.setName("Sefiller");
	    book2.setType("Dram");
	   
	    
	    
		List<Book> books = Arrays.asList(book1, book2);
		
		Mockito
			.when(bookRepository.getAllBookList())
			.thenReturn(books);
		
		
		
		List<Book> books2 = bookService.getAllBookList();
		
		Assert.assertNotNull(books2);
		Assert.assertTrue(books2.size() > 0);
	}
	
	@Test
	public void saveBook() {
		
		BookContext bookContext = new BookContext();
		bookContext.setName("Cimri");
		bookContext.setType("Polisiye");
	   
		
	    Long maxBookId = 100L;
	    
	    Book book = new Book();
	    book.setBookNo(maxBookId + 1);
	    book.setName(bookContext.getName());
	    book.setType(bookContext.getType());
	    
		
	    Mockito
			.when(bookRepository.findMaxId())
			.thenReturn(maxBookId);
	    
		Mockito
			.when(bookRepository.save(book))
			.thenReturn(book);
		
		
		long bookId = bookService.save(bookContext);
		
		Assert.assertEquals(101L, bookId);
	}
	
	@Test
	public void saveBookAsDraft() {
		
		long bookNo = 1008L;
		prepareMockTestRuleBookDraftSave(bookNo);
		
		long returnedBookNo = bookService.saveAsDraft(bookNo);
		
		Assert.assertEquals(bookNo, returnedBookNo);
	}
	
	private void prepareMockTestRuleBookQueryByBookNo() {
		
		Book book = new Book();
		book.setBookNo(10L);
		book.setName("Cimri");
		book.setType("Polisiye");
	   
		 
	    Mockito
	    	.when(bookRepository.findWithBookNo(10L))
	    	.thenReturn(book);
		
	}
	
	private void prepareMockTestRuleBookProfiles() {
		
		Book book1 = new Book();
		book1.setBookNo(10L);
		book1.setName("Cimri");
		book1.setType("Polisiye");
	   
	    
	    Book book2 = new Book();
	    book2.setBookNo(10L);
	    book2.setName("Sefiller");
	    book2.setType("Dram");
	    
	    
		List<Book> books = Arrays.asList(book1, book2);
		
		Mockito
			.when(bookRepository.getAllBookList())
			.thenReturn(books);
		
	}
	
	private void prepareMockTestRuleBookSave(BookContext bookContext) {
		
		Long bookId = 100L;
		Book book = new Book();
		book.setBookNo(bookId + 1);
		book.setName(bookContext.getName());
		book.setType(bookContext.getType());
	 
	    Mockito
			.when(bookRepository.findMaxId())
			.thenReturn(bookId);
	    
		Mockito
			.when(bookRepository.save(book))
			.thenReturn(book);
	}
	
	private void prepareMockTestRuleBookDraftSave(Long bookNo) {
		
		Book book = new Book();
		book.setBookNo(bookNo);
		book.setName("Sefiller");
		book.setType("Dram");
		
		
		Mockito
			.when(bookRepository.findWithBookNo(bookNo))
			.thenReturn(book);
		
		BookDraft bookDraft = new BookDraft();
		Mockito
			.when(bookDraftRepository.save(bookDraft))
			.thenReturn(bookDraft);
	}
}
