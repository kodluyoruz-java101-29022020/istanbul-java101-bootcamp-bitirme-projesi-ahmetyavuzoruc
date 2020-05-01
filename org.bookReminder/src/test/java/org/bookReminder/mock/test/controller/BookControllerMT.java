package org.bookReminder.mock.test.controller;

import java.util.Arrays;
import java.util.List;

import org.bookReminder.controller.BookController;
import org.bookReminder.service.BookService;
import org.bookReminder.service.model.BookProfile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;


@RunWith(MockitoJUnitRunner.class)
public class BookControllerMT {

	@Mock
	private BookService bookService;
	
	@InjectMocks
	private BookController bookController;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllBookProfileListWithApiKey() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("x-api-key", "Yavuz");
        
		
		List<BookProfile> profiles = Arrays.asList(new BookProfile());
		
		Mockito
			.when(bookService.getAllBookProfileList(1))
			.thenReturn(profiles);
        
        
		ResponseEntity<List<BookProfile>> bookList = bookController.getAllBookProfileList(1, request);
	
        Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(bookList.getStatusCode()));
        Assert.assertEquals(1, bookList.getBody().size());
	}
	
	private void prepareMockTestRuleBookProfileList() {
		
		List<BookProfile> profiles = Arrays.asList(new BookProfile());
		
		Mockito
			.when(bookService.getAllBookProfileList(1))
			.thenReturn(profiles);
	}
	
}
