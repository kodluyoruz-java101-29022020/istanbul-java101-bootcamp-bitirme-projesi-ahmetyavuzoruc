package org.bookReminder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bookReminder.dao.entity.Book;
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
	
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public Book findByBookNo(@PathVariable("id") Long id) {
		
		return bookService.findByBookNo(id);
	}
	
	@RequestMapping(value = "/book/list", method = RequestMethod.GET)
	public List<Book> getAllBookList() {
		
		return bookService.getAllBookList();
	}
	
	@RequestMapping(value = "/book/profile/list", method = RequestMethod.GET)
	public ResponseEntity<List<BookProfile>> 
		getAllBookProfileList(@RequestParam("size") Integer upperLimit, HttpServletRequest httpRequest) {
		
		String apikey = (String)httpRequest.getHeader("x-api-key");
		
		if(apikey == null) {
			throw new RuntimeException("Set x-api-key error!!!");
		}
		else if(!apikey.equals("Yavuz")) {
			throw new RuntimeException("Invalid x-api-key error!!!");
		}
		
		List<BookProfile> profiles = bookService.getAllBookProfileList(upperLimit);
		
		ResponseEntity<List<BookProfile>> response = 
				new ResponseEntity<List<BookProfile>>(profiles, HttpStatus.UNAUTHORIZED);
		
		return response;
	}
	
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public Long save(@RequestBody BookContext bookContext) {
		
		return bookService.save(bookContext);
	}
	
	@RequestMapping(value = "/book/draft", method = RequestMethod.POST)
	public Long saveAsDraft(@RequestParam("id") Long id) {
		
		return bookService.saveAsDraft(id);
	}
	
}
