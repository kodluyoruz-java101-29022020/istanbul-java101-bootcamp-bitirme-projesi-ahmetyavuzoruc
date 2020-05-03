package org.bookReminder.integration.test.controller;


import java.util.List;

import org.bookReminder.dao.jpa.entity.Book;
import org.bookReminder.service.model.BookContext;
import org.bookReminder.service.model.BookProfile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({ "classpath:application.properties" })
public class BookControllerIT {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int tomcatPortNo;
	
	
	
	@Test
	@Order(1)
	public void testRestTemplate() {
		
		ResponseEntity<String> response = restTemplate.getForEntity("https://www.google.com", String.class);
		
		System.out.println(tomcatPortNo);
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(response.getBody().length() > 0);
	}
	
	@Test
	@Order(2)
	public void findBookById() {
		
		String url = prepareBookRestServiceRootUrl() + "book/10003";
		
		ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);
		
		Book emp = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(10003 == emp.getBookNo());
	}
	
	@Test
	@Order(3)
	public void saveBook() {
		
		String url = prepareBookRestServiceRootUrl() + "book";
		
		BookContext bookContext = new BookContext();
		bookContext.setName("TestUser1");
		bookContext.setType("testUser1");
	
		
		
		ResponseEntity<Long> response = restTemplate.postForEntity(url, bookContext, Long.class);
		
		Long bookNo = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertNotNull(bookNo);
	}
	
	@Test
	@Order(4)
	public void getAllBookProfileList() {
		
		String url = prepareBookRestServiceRootUrl() + "book/profile/list?size=1";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("x-api-key", "Yavuz");
		
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);
		
		ResponseEntity<List<BookProfile>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				httpEntity, 
				new ParameterizedTypeReference<List<BookProfile>>() {} );
		
		List<BookProfile> profiles = response.getBody();
		
		Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
		Assert.assertNotNull(profiles);
		Assert.assertEquals(1, profiles.size());
	}
	
	private String prepareBookRestServiceRootUrl() {
		
		return "http://localhost:" + tomcatPortNo + "/application/";
	}
}
