package org.bookReminder.service;

import java.math.BigInteger;
import java.util.List;

import org.bookReminder.dao.entity.Writer;
import org.bookReminder.dao.jpa.repository.WriterRepository;
import org.bookReminder.dao.mongo.entity.WriterDraft;
import org.bookReminder.dao.mongo.entity.repository.WriterDraftRepository;
import org.bookReminder.service.model.WriterContext;
import org.bookReminder.service.model.WriterProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WriterService {

	@Autowired
	private WriterRepository writerRepository;
	
	@Autowired
	private WriterDraftRepository writerDraftRepository;
	
	
	public Writer findByWrtNo(Long wrtNo) {
		
		return writerRepository.findWithWrtNo(wrtNo);
	}
	
	public List<Writer> getAllWriterList() {
		
		return writerRepository.getAllWriterList();
	}
	
	public List<WriterProfile> getAllWriterProfileList(int upperLimit) {
		
		return writerRepository.getAllWriterProfileList(PageRequest.of(0, upperLimit));
	}
	
	@Transactional
	public Long save(WriterContext writerContext) {
		
		Long maxId = writerRepository.findMaxId() + 1;
		
		Writer writer = new Writer();
		writer.setWrtNo(maxId);
		writer.setName(writerContext.getName());
		writer.setLastName(writerContext.getLastName());
		
		
		writer = writerRepository.save(writer);
		
		
		return writer.getWrtNo();
	}
	
	public Long saveAsDraft(Long wrtNo) {
		
		Writer writer = writerRepository.findWithWrtNo(wrtNo);
		
		if(writer == null) {
			return -1L;
		}
		
		WriterDraft draft = new WriterDraft();
		
		draft.setWrtNo(BigInteger.valueOf(writer.getWrtNo()));
		draft.setLastName(writer.getLastName());
		draft.setName(writer.getName());
		
		writerDraftRepository.save(draft);
		return writer.getWrtNo();
	}
}
