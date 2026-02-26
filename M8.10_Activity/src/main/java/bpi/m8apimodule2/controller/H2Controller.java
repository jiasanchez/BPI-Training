package bpi.m8apimodule2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bpi.m8apimodule2.dto.BookDTO;
import bpi.m8apimodule2.service.BookService;

@RestController
@RequestMapping("/api/H2Activity")
public class H2Controller {

		private final BookService bookService;
		
		public H2Controller(BookService bookService) {
			this.bookService = bookService;
		}
		
		@GetMapping
		public List<BookDTO> getBooks(@RequestParam(required = false) String title){
			return bookService.findAllBooks();
			}
		}

