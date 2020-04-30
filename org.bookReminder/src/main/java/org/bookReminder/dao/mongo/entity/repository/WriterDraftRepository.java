package org.bookReminder.dao.mongo.entity.repository;

import java.math.BigInteger;


import org.bookReminder.dao.mongo.entity.WriterDraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterDraftRepository extends MongoRepository<WriterDraft, BigInteger>{

	@Query(value = "{ 'empNo': ?0 }")
	public WriterDraft findWriterByEmpNumber(Long id);
}
