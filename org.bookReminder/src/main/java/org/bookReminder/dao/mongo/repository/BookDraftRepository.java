package org.bookReminder.dao.mongo.repository;



import org.bookReminder.dao.mongo.entity.BookDraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookDraftRepository extends MongoRepository<BookDraft, Long>{

	@Query(value = "{ 'book_No': ?0 }")
	public BookDraft findWriterByBookNumber(Long bookNo);
}
