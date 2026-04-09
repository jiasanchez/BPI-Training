package m8.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
import m8.project.dto.BookDTO;
import m8.project.dto.LoanDTO;
import m8.project.entity.Book;
import m8.project.service.BookService;
import m8.project.service.LoanService;
import m8.project.service.UserService;

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
		
		@GetMapping 
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
		@GetMapping("/{id}")
		public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
			BookDTO bookDTO = bookService.findById(id);
			return ResponseEntity.ok(bookDTO);
		}
		
		@PostMapping
		public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
			BookDTO savedBook = bookService.addBook(bookDTO);
			return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<BookDTO> updateBook(@Valid @PathVariable Long id,
												  @RequestBody BookDTO bookDTO){
			BookDTO updateBook = bookService.updateBook(id, bookDTO);
			return ResponseEntity.ok(updateBook);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteBook(@PathVariable Long id){
			bookService.deleteBook(id);
			return ResponseEntity.ok("Successfully Deleted the Book ID: " + id);
		}
		
		@GetMapping("/loans")
		public ResponseEntity<List<LoanDTO>> getAllLoans(){
			List<LoanDTO> loans = loanService.getAllLoans();
			return ResponseEntity.ok(loans);
		}
		
		@PostMapping("/borrow")
		public ResponseEntity<LoanDTO> borrowBook(@RequestBody LoanDTO loanDTO){
			LoanDTO borrowedBook = loanService.borowBook(loanDTO);
			return new ResponseEntity<>(borrowedBook, HttpStatus.CREATED);
		}
		
		@PostMapping("/return")
		public ResponseEntity<LoanDTO> returnBook(@RequestParam Long loanId){
			LoanDTO returnBook = loanService.returnBook(loanId);
			return ResponseEntity.ok(returnBook);
		}
		
		//JSON EXCEPTION HANDLER
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<String> InvalidInputHandler(MethodArgumentNotValidException ex){
			String errorMessage = ex.getBindingResult().getFieldErrors()
									.stream()
									.findFirst()
									.map(error -> error.getDefaultMessage())
									.orElse("Invalid input");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(errorMessage);
		}
		@ExceptionHandler(HttpMessageNotReadableException.class)
		public ResponseEntity<String> InvalidFormatHandler(HttpMessageNotReadableException ex){
			return ResponseEntity
					.badRequest()
					.body("Invalid Input: Please make sure field have correct values.");
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> ExceptionHandler(Exception ex){
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ex.getMessage());
		}
		
}
