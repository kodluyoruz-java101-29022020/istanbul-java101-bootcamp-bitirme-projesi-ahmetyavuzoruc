package org.bookReminder.web.ui.controller;


import java.util.List;


import org.bookReminder.dao.entity.Book;
import org.bookReminder.service.BookService;
import org.bookReminder.service.model.BookContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/pages")
public class PageController {

	@Autowired
	private BookService bookService;
	
	
	@RequestMapping(value = "/book/list", method = RequestMethod.GET)
	public String getBooks(Model model) {
		
		List<Book> books = bookService.getAllBookList();
		model.addAttribute("books", books);
		
		return "thyme_book_list";
	}
	
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String getBookSavePage(BookContext bookContext) {
		
		return "thyme_book_save";
	}
	
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
    public String save(BookContext bookContext, BindingResult result, Model model) {
        
		
		bookService.save(bookContext);
        
		model.addAttribute("books", bookService.getAllBookList());
        
        return "thyme_book_list";
    }
}
