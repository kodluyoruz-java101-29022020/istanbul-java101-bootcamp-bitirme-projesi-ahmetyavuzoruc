package org.bookReminder.service;

import java.math.BigInteger;
import java.util.List;

import org.bookReminder.dao.jpa.entity.Book;
import org.bookReminder.dao.jpa.repository.BookRepository;
import org.bookReminder.dao.mongo.entity.BookDraft;
import org.bookReminder.dao.mongo.repository.BookDraftRepository;
import org.bookReminder.service.model.BookContext;
import org.bookReminder.service.model.BookProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {


	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookDraftRepository bookDraftRepository;
	
	
	public Book findByBookNo(Long bookNo) {
		
		return bookRepository.findWithBookNo(bookNo);
	}
	
	public List<Book> getAllBookList() {
		
		return bookRepository.getAllBookList();
	}
	
	public List<BookProfile> getAllBookProfileList(int upperLimit) {
		
		return bookRepository.getAllBookProfileList(PageRequest.of(0, upperLimit));
	}
	
	@Transactional
	public Long save(BookContext bookContext) {
		
		Long maxId = bookRepository.findMaxBookNo() + 1;
		
		Book book = new Book();
		book.setBookNo(maxId);
		book.setName(bookContext.getName());
		book.setType(bookContext.getType());
		
		
		book = bookRepository.save(book);
		
		
		return book.getBookNo();
	}
	
	public Long saveAsDraft(Long bookNo) {
		
		Book book = bookRepository.findWithBookNo(bookNo);
		
		if(book == null) {
			return -1L;
		}
		
		BookDraft draft = new BookDraft();
		
		draft.setBookNo(Long.valueOf(book.getBookNo()));
		draft.setType(book.getType());
		draft.setName(book.getName());
		
		bookDraftRepository.save(draft);
		return book.getBookNo();
	}
}