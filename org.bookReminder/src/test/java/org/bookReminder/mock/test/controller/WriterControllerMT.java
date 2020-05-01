package org.bookReminder.mock.test.controller;

import java.util.Arrays;
import java.util.List;

import org.bookReminder.controller.WriterController;
import org.bookReminder.service.WriterService;
import org.bookReminder.service.model.WriterProfile;
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
public class WriterControllerMT {

	@Mock
	private WriterService writerService;
	
	@InjectMocks
	private WriterController writerController;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllWriterProfileListWithApiKey() {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("x-api-key", "Yavuz");
        
		
		List<WriterProfile> profiles = Arrays.asList(new WriterProfile());
		
		Mockito
			.when(writerService.getAllWriterProfileList(1))
			.thenReturn(profiles);
        
        
		ResponseEntity<List<WriterProfile>> writerList = writerController.getAllWriterProfileList(1, request);
	
        Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(writerList.getStatusCode()));
        Assert.assertEquals(1, writerList.getBody().size());
	}
	
	private void prepareMockTestRuleWriterProfileList() {
		
		List<WriterProfile> profiles = Arrays.asList(new WriterProfile());
		
		Mockito
			.when(writerService.getAllWriterProfileList(1))
			.thenReturn(profiles);
	}
	
}
