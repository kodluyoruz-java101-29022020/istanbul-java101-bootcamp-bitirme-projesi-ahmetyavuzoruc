package org.bookReminder.integration.test.controller;


import java.util.List;

import org.bookReminder.dao.entity.Writer;
import org.bookReminder.service.model.WriterContext;
import org.bookReminder.service.model.WriterProfile;
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
public class WriterControllerIT {

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
	public void findWriterById() {
		
		String url = prepareWriterRestServiceRootUrl() + "writer/10003";
		
		ResponseEntity<Writer> response = restTemplate.getForEntity(url, Writer.class);
		
		Writer emp = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertTrue(10003 == emp.getWrtNo());
	}
	
	@Test
	@Order(3)
	public void saveWriter() {
		
		String url = prepareWriterRestServiceRootUrl() + "writer";
		
		WriterContext writerContext = new WriterContext();
		writerContext.setName("TestUser1");
		writerContext.setLastName("testUser1");
		writerContext.setGender("F");
		
		
		ResponseEntity<Long> response = restTemplate.postForEntity(url, writerContext, Long.class);
		
		Long wrtNo = response.getBody();
		
		Assert.assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
		Assert.assertNotNull(wrtNo);
	}
	
	@Test
	@Order(4)
	public void getAllWriterProfileList() {
		
		String url = prepareWriterRestServiceRootUrl() + "writer/profile/list?size=1";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("x-api-key", "Yavuz");
		
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);
		
		ResponseEntity<List<WriterProfile>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				httpEntity, 
				new ParameterizedTypeReference<List<WriterProfile>>() {} );
		
		List<WriterProfile> profiles = response.getBody();
		
		Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
		Assert.assertNotNull(profiles);
		Assert.assertEquals(1, profiles.size());
	}
	
	private String prepareWriterRestServiceRootUrl() {
		
		return "http://localhost:" + tomcatPortNo + "/application/";
	}
}
