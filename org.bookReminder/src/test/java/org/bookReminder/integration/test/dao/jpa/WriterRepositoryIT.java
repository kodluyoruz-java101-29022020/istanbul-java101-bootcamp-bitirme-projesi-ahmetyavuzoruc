package org.bookReminder.integration.test.dao.jpa;


import java.util.List;

import org.bookReminder.dao.entity.Writer;
import org.bookReminder.dao.jpa.repository.WriterRepository;
import org.bookReminder.service.model.WriterProfile;
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
public class WriterRepositoryIT {

	@Autowired
	private WriterRepository writerRepository;
	
	
	@Test
	@Order(1)
	public void selectWriterByWrtNo() {
		
		Long maxId = writerRepository.findMaxId();
		Writer writer = writerRepository.findWithWrtNo(maxId);
		
		Assert.assertNotNull(writer);
		Assert.assertTrue(writer.getWrtNo() > 0);
	}
	
	@Test
	@Order(2)
	public void selectAllWriterProfileList() {
		
		List<WriterProfile> writerProfileList = writerRepository.getAllWriterProfileList(PageRequest.of(0, 1));
		
		Assert.assertEquals(writerProfileList.size(), 1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	@Order(3)
	public void saveWriter() {
		
		Long maxId = writerRepository.findMaxId();
		Long newWriterId = maxId + 1;
		
		Writer writer = new Writer();
		writer.setWrtNo(newWriterId);
		writer.setName("Ahmet");
		writer.setLastName("Oruc");
		
		
		
		writerRepository.save(writer);
		
		writer = writerRepository.findWithWrtNo(newWriterId);
		
		Assert.assertNotNull(writer);
		Assert.assertTrue(writer.getWrtNo() > 0);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Order(4)
	public void updateWriter() {
		
		Long maxId = writerRepository.findMaxId();
		Writer writer = writerRepository.findWithWrtNo(maxId);
		
		writer.setName("Ayşe");
		writer.setLastName("Hürel");
		
		
		writerRepository.save(writer);
		
		writer = writerRepository.findWithWrtNo(maxId);
		
		Assert.assertEquals("Ayşe", writer.getName());
		Assert.assertEquals("Hürel", writer.getLastName());
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void deleteWriter() {
		
		Long maxId = writerRepository.findMaxId();
		Writer writer = writerRepository.findWithWrtNo(maxId);
		
		writerRepository.delete(writer);
		
		writer = writerRepository.findWithWrtNo(maxId);
		
		Assert.assertNull(writer);
	}
}
