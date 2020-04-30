package org.bookReminder.dao.jpa.repository;

import java.util.List;

import org.bookReminder.dao.entity.Writer;
import org.bookReminder.service.model.WriterProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends CrudRepository<Writer, Long> {

	@Query(value = "FROM Writer e WHERE e.wrtNo = :wrtNo")
	public Writer findWithWrtNo(@Param("wrtNo") Long wrtNo);
	
	@Query(value = "SELECT MAX(e.wrtNo) FROM Writer e")
	public Long findMaxId();
	
	@Query(value = "SELECT e FROM Writer e")
	public List<Writer> getAllWriterList();
	
	@Query(value = "SELECT new org.bookReminder.service.model.WriterProfile(wrt, wrtBook.name) FROM Writer wrt LEFT OUTER JOIN wrt.books wrtBook")
	public List<WriterProfile> getAllWriterProfileList(Pageable pageable);
}
