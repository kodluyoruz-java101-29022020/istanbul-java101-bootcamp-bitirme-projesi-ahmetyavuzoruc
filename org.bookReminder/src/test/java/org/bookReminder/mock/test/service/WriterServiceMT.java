package org.bookReminder.mock.test.service;

import java.util.Arrays;
import java.util.List;

import org.bookReminder.dao.entity.Writer;
import org.bookReminder.dao.jpa.repository.WriterRepository;
import org.bookReminder.dao.mongo.entity.WriterDraft;
import org.bookReminder.dao.mongo.entity.repository.WriterDraftRepository;
import org.bookReminder.service.WriterService;
import org.bookReminder.service.model.WriterContext;
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
public class WriterServiceMT {

	@Mock
    private WriterRepository writerRepository;
	
	@Mock
	private WriterDraftRepository writerDraftRepository;
	
	@InjectMocks
	private WriterService writerService;
	
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void queryWriter() {
		
		Writer wrt = new Writer();
		wrt.setWrtNo(10L);
		wrt.setName("Mehmet");
		wrt.setLastName("Oruc");
	  
	    
		Writer wrt3 = new Writer();
		wrt3.setWrtNo(11L);
		wrt3.setName("Hatice");
		wrt3.setLastName("Oruc");
	   
		 
	    Mockito
	    	.when(writerRepository.findWithWrtNo(10L))
	    	.thenReturn(wrt);
	    
	    Mockito
	    	.when(writerRepository.findWithWrtNo(11L))
	    	.thenReturn(wrt3);
	    
		
		Writer wrt2 = writerService.findByWrtNo(11L);
		
		Assert.assertNotNull(wrt2);
		Assert.assertTrue(wrt2.getWrtNo() > 0);
	}
	
	@Test
	public void queryAllWriters() {
		
		Writer wrt1 = new Writer();
		wrt1.setWrtNo(10L);
		wrt1.setName("Mehmet");
		wrt1.setLastName("Oruc");
	   
	    
	    Writer wrt2 = new Writer();
	    wrt2.setWrtNo(10L);
	    wrt2.setName("Hatice");
	    wrt2.setLastName("Oruc");
	   
	    
	    
		List<Writer> writers = Arrays.asList(wrt1, wrt2);
		
		Mockito
			.when(writerRepository.getAllWriterList())
			.thenReturn(writers);
		
		
		
		List<Writer> writers2 = writerService.getAllWriterList();
		
		Assert.assertNotNull(writers2);
		Assert.assertTrue(writers2.size() > 0);
	}
	
	@Test
	public void saveWriter() {
		
		WriterContext wrtContext = new WriterContext();
		wrtContext.setName("Mehmet");
		wrtContext.setLastName("Oruc");
	   
		
	    Long maxWriterId = 100L;
	    
	    Writer wrt = new Writer();
	    wrt.setWrtNo(maxWriterId + 1);
	    wrt.setName(wrtContext.getName());
	    wrt.setLastName(wrtContext.getLastName());
	    
		
	    Mockito
			.when(writerRepository.findMaxId())
			.thenReturn(maxWriterId);
	    
		Mockito
			.when(writerRepository.save(wrt))
			.thenReturn(wrt);
		
		
		long wrtId = writerService.save(wrtContext);
		
		Assert.assertEquals(101L, wrtId);
	}
	
	@Test
	public void saveWriterAsDraft() {
		
		long wrtNo = 1008L;
		prepareMockTestRuleWriterDraftSave(wrtNo);
		
		long returnedWrtNo = writerService.saveAsDraft(wrtNo);
		
		Assert.assertEquals(wrtNo, returnedWrtNo);
	}
	
	private void prepareMockTestRuleWriterQueryByWrtNo() {
		
		Writer wrt = new Writer();
		wrt.setWrtNo(10L);
		wrt.setName("Mehmet");
		wrt.setLastName("Oruc");
	   
		 
	    Mockito
	    	.when(writerRepository.findWithWrtNo(10L))
	    	.thenReturn(wrt);
		
	}
	
	private void prepareMockTestRuleWriterProfiles() {
		
		Writer wrt1 = new Writer();
		wrt1.setWrtNo(10L);
		wrt1.setName("Mehmet");
		wrt1.setLastName("Oruc");
	   
	    
	    Writer wrt2 = new Writer();
	    wrt2.setWrtNo(10L);
	    wrt2.setName("Hatice");
	    wrt2.setLastName("Oruc");
	    
	    
		List<Writer> writers = Arrays.asList(wrt1, wrt2);
		
		Mockito
			.when(writerRepository.getAllWriterList())
			.thenReturn(writers);
		
	}
	
	private void prepareMockTestRuleWriterSave(WriterContext wrtContext) {
		
		Long writerId = 100L;
		Writer wrt = new Writer();
		wrt.setWrtNo(writerId + 1);
		wrt.setName(wrtContext.getName());
		wrt.setLastName(wrtContext.getLastName());
	 
	    Mockito
			.when(writerRepository.findMaxId())
			.thenReturn(writerId);
	    
		Mockito
			.when(writerRepository.save(wrt))
			.thenReturn(wrt);
	}
	
	private void prepareMockTestRuleWriterDraftSave(Long wrtNo) {
		
		Writer wrt = new Writer();
		wrt.setWrtNo(wrtNo);
		wrt.setName("Hatice");
		wrt.setLastName("Oruc");
		
		
		Mockito
			.when(writerRepository.findWithWrtNo(wrtNo))
			.thenReturn(wrt);
		
		WriterDraft writerDraft = new WriterDraft();
		Mockito
			.when(writerDraftRepository.save(writerDraft))
			.thenReturn(writerDraft);
	}
}
