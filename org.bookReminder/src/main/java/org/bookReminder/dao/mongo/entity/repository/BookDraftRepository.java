package org.bookReminder.dao.mongo.entity.repository;

import java.math.BigInteger;

import org.bookReminder.dao.mongo.entity.BookDraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDraftRepository extends MongoRepository<BookDraft, BigInteger>{

	@Query(value = "{ 'bookNo': ?0 }")
	public BookDraft findWriterByBookNumber(Long id);
}
