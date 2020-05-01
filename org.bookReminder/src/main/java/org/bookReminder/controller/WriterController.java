package org.bookReminder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bookReminder.dao.entity.Writer;
import org.bookReminder.service.WriterService;
import org.bookReminder.service.model.WriterContext;
import org.bookReminder.service.model.WriterProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/application")
public class WriterController {

	@Autowired
	private WriterService writerService;
	
	
	@RequestMapping(value = "/writer/{id}", method = RequestMethod.GET)
	public Writer findByWrtNo(@PathVariable("id") Long id) {
		
		return writerService.findByWrtNo(id);
	}
	
	@RequestMapping(value = "/writer/list", method = RequestMethod.GET)
	public List<Writer> getAllEmployeeList() {
		
		return writerService.getAllWriterList();
	}
	
	@RequestMapping(value = "/writer/profile/list", method = RequestMethod.GET)
	public ResponseEntity<List<WriterProfile>> 
		getAllWriterProfileList(@RequestParam("size") Integer upperLimit, HttpServletRequest httpRequest) {
		
		String apikey = (String)httpRequest.getHeader("x-api-key");
		
		if(apikey == null) {
			throw new RuntimeException("Set x-api-key error!!!");
		}
		else if(!apikey.equals("Yavuz")) {
			throw new RuntimeException("Invalid x-api-key error!!!");
		}
		
		List<WriterProfile> profiles = writerService.getAllWriterProfileList(upperLimit);
		
		ResponseEntity<List<WriterProfile>> response = 
				new ResponseEntity<List<WriterProfile>>(profiles, HttpStatus.UNAUTHORIZED);
		
		return response;
	}
	
	
	@RequestMapping(value = "/writer", method = RequestMethod.POST)
	public Long save(@RequestBody WriterContext writerContext) {
		
		return writerService.save(writerContext);
	}
	
	@RequestMapping(value = "/writer/draft", method = RequestMethod.POST)
	public Long saveAsDraft(@RequestParam("id") Long id) {
		
		return writerService.saveAsDraft(id);
	}
	
}
