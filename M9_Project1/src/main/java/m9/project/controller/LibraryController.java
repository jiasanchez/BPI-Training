package m9.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import m9.project.dto.BookDTO;
import m9.project.dto.LoanDTO;
import m9.project.entity.Book;
import m9.project.service.BookService;
import m9.project.service.LoanService;
import m9.project.service.UserService;

@RestController
@RequestMapping("/library")
public class LibraryController {

		private final BookService bookService;
		private final LoanService loanService;

		
		public LibraryController(BookService bookService,
								 LoanService loanService) {
			this.bookService = bookService;
			this.loanService = loanService;

		}
		
		@GetMapping("/books")
		public ResponseEntity<List<BookDTO>> getAllBooks(){
			List<BookDTO> books = bookService.getAllBooks();
			return ResponseEntity.ok(books);
		}
		
		@GetMapping("/books/available")
		public ResponseEntity<List<BookDTO>> getAvailableBooks(){
			List<BookDTO> books = bookService.getAvailableBooks();
			return ResponseEntity.ok(books);
		}
		@GetMapping("/books/borrowed")
		public ResponseEntity<List<BookDTO>> getBorrowedBooks(){
			List<BookDTO> books = bookService.getBorrowedBooks();
			return ResponseEntity.ok(books);
		}
		@GetMapping("/books/{id}")
		public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
			BookDTO bookDTO = bookService.findById(id);
			return ResponseEntity.ok(bookDTO);
		}
		
		@PostMapping("/add")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
			BookDTO savedBook = bookService.addBook(bookDTO);
			return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
		}
		
		@PutMapping("/update/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<BookDTO> updateBook(@Valid @PathVariable Long id,
												  @RequestBody BookDTO bookDTO){
			BookDTO updateBook = bookService.updateBook(id, bookDTO);
			return ResponseEntity.ok(updateBook);
		}
		
		@DeleteMapping("/delete/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<String> deleteBook(@PathVariable Long id){
			bookService.deleteBook(id);
			return ResponseEntity.ok("Successfully Deleted the Book ID: " + id);
		}
		
		@GetMapping("/loans")
		public ResponseEntity<List<LoanDTO>> getAllLoans(){
			List<LoanDTO> loans = loanService.getAllLoans();
			return ResponseEntity.ok(loans);
		}

		
}
