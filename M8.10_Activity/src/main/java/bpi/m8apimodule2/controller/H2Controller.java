package bpi.m8apimodule2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bpi.m8apimodule2.dto.BookDTO;
import bpi.m8apimodule2.model.Book;
import bpi.m8apimodule2.service.BookService;

@RestController
@RequestMapping("/api/H2Activity")
public class H2Controller {

		private final BookService bookService;
		
		public H2Controller(BookService bookService) {
			this.bookService = bookService;
		}
		
		@GetMapping
		public List<Book> getBooks(@RequestParam(required = false) String title){
			return bookService.findAllBooks();
			}
		@PostMapping
		public BookDTO addBook(@RequestBody BookDTO bookDTO) {
			return bookService.addBook(bookDTO);
		}
		
		@PutMapping("/{id}")
		public Book updateBook(@PathVariable Long id, @RequestBody Book book){
			return bookService.updateMovie(id, book);
		}
		
		@DeleteMapping("/{id}")
		public String deleteBook(@PathVariable Long id) {
			bookService.deleteBook(id);
			return "Book Successfully deleted: " + id;
		}
		}

