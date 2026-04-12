 /**
 * REST Controller for managing book shop operations.
 *
 * <p>This controller exposes endpoints for retrieving and managing books.
 * Access to certain endpoints is restricted based on user roles using Spring Security.
 *
 * <p>Base URL: {@code /api/bookshop}
 */

package project.bookshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import project.bookshop.dto.BookDTO;
import project.bookshop.entity.Book;
import project.bookshop.service.BookShopService;

@RestController
@RequestMapping("/api/bookshop")
public class BookShopController {
		private final BookShopService bookShopService;
		/**
	     * Constructs a new {@code BookShopController}.
	     *
	     * @param bookShopService service layer used to handle book operations
	     */
		public BookShopController(BookShopService bookShopService) {
			this.bookShopService = bookShopService;
		}
		
		  /**
	     * Retrieves all books from the system.
	     * 
	     * <p>This end point is restricted to users with the ADMIN role only.
	     *
	     * @return ResponseEntity containing a list of {@link BookDTO}
	     *         and HTTP status 200 (OK)
	     */
		@GetMapping
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<List<BookDTO>> getAllBooks(){
			List<BookDTO> books = bookShopService.getAllBooks();
			return ResponseEntity.ok(books);
		}
		
		/**
	     * Creates a new book
	     * 
	     * <p>This end point is restricted to users with the ADMIN role only.
	     *
	     * @param bookDTO the book DTO containing book details
	     * @return ResponseEntity containing the created BookDTO 
	     *         and HTTP status 201 (CREATED)
	     */
		@PostMapping
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO booKDTO){
			BookDTO savedBook = bookShopService.addBook(booKDTO);
			return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
		}
		
		/**
	     * Updates an existing book by its ID
	     * 
	     * <p>This end point is restricted to users with the ADMIN role only.
	     *
	     * @param id is the ID of the book to update
	     * @param bookDTO the updated book DTO
	     * @return ResponseEntity containing the updated BookDTO 
	     *         and HTTP status 200 (OK)
	     */
		@PutMapping("/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<BookDTO> updateBook(@Valid @PathVariable Long id,
												  @RequestBody BookDTO bookDTO){
			BookDTO updateBook = bookShopService.updateBook(id, bookDTO);
			return ResponseEntity.ok(updateBook);
		}
		
		/**
	     * Deletes a book by its ID
	     * 
	     * <p>This end point is restricted to users with the ADMIN role only.
	     *
	     * @param id is the ID of the book to delete
	     * @return ResponseEntity containing a success message 
	     *         and HTTP status 200 (OK)
	     */
		@DeleteMapping("/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<String> deleteBook(@PathVariable Long id){
			bookShopService.deleteBook(id);
			return ResponseEntity.ok("Successfully Deleted Book ID: " + id);
		}
}
