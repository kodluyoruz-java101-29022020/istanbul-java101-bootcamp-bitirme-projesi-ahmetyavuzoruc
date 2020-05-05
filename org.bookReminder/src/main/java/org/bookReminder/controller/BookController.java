package org.bookReminder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bookReminder.dao.jpa.entity.Book;
import org.bookReminder.service.BookService;
import org.bookReminder.service.model.BookContext;
import org.bookReminder.service.model.BookProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/application")
public class BookController {

	@Autowired
	private BookService bookService;
	
	
	@RequestMapping(value = "/book/{book_No}", method = RequestMethod.GET)
	public Book findByBookNo(@PathVariable("book_No") Long bookNo) {
		
		return bookService.findByBookNo(bookNo);
	}
	
	@RequestMapping(value = "/book/list", method = RequestMethod.GET)
	public List<Book> getAllBookList() {
		
		return bookService.getAllBookList();
	}
	
	
	@RequestMapping(value = "/book/save", method = RequestMethod.POST)
	public Long save(@RequestBody BookContext bookContext) {
		
		return bookService.save(bookContext);
	}

	@RequestMapping(value = "/book/draft", method = RequestMethod.POST)
	public Long saveAsDraft(@RequestParam("book_No") Long bookNo) {
		
		return bookService.saveAsDraft(bookNo);
	}
	
	
	
	
	
}
