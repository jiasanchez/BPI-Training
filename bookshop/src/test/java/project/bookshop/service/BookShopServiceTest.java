package project.bookshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import project.bookshop.dto.BookDTO;
import project.bookshop.entity.Book;
import project.bookshop.exception.BookDeletionNotAllowedException;
import project.bookshop.exception.ResourceNotFoundException;
import project.bookshop.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookShopServiceTest {

	@Mock
	private BookRepository bookRepo;
	
	@InjectMocks
	private BookShopService bookShopService;
	
	private Book book;
	private BookDTO bookDTO;
	
	@BeforeEach
	void initialSetup() {
		book = new Book();
		book.setId(1L);
		book.setTitle("Final Project: Bookshop");
		book.setAuthor("John Isiah Sanchez");
		book.setPrice(100.0);
		book.setCreatedAt(LocalDate.now().minusWeeks(5));
		
		bookDTO = new BookDTO();
		bookDTO.setId(1L);
		bookDTO.setTitle("Final Project: Bookshop");
		book.setAuthor("John Isiah Sanchez");
		book.setPrice(100.0);
		book.setCreatedAt(LocalDate.now().minusWeeks(5));
	}
	
	/**
	 * Method: getAllBooks
	 * Scenario: Books exist in the repository
	 * Expected: Returns a non-empty list of BookDTO
	 */
	@Test
	void getAllBooks_whenBooksExist_returnsList() {
	    // Arrange
	    when(bookRepo.findAll()).thenReturn(List.of(book));

	    // Act
	    List<BookDTO> result = bookShopService.getAllBooks();

	    // Assert
	    assertEquals(1, result.size());
	    verify(bookRepo).findAll();
	}
	/**
	 * Method: addBook
	 * Scenario: Valid BookDTO is provided
	 * Expected: Book is saved and returned as DTO
	 */
	@Test
	void addBook_whenValidInput_savesAndReturnsDTO() {
	    // Arrange
	    when(bookRepo.save(any(Book.class))).thenReturn(book);

	    // Act
	    BookDTO result = bookShopService.addBook(bookDTO);

	    // Assert
	    assertNotNull(result);
	    verify(bookRepo).save(any(Book.class));
	}

	/**
	 * Method: updateBook
	 * Scenario: Book exists and valid update data is provided
	 * Expected: Book is updated and returned as DTO
	 */
	@Test
	void updateBook_whenBookExists_updatesAndReturnsDTO() {
	    // Arrange
	    when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
	    when(bookRepo.save(any(Book.class))).thenReturn(book);

	    bookDTO.setTitle("Updated");

	    // Act
	    BookDTO result = bookShopService.updateBook(1L, bookDTO);

	    // Assert
	    assertEquals("Updated", result.getTitle());
	    verify(bookRepo).save(any(Book.class));
	}

	/**
	 * Method: updateBook
	 * Scenario: Book does not exist in the database
	 * Expected: ResourceNotFoundException is thrown
	 */
	@Test
	void updateBook_whenBookNotFound_throwsResourceNotFoundException() {
	    // Arrange
	    when(bookRepo.findById(1L)).thenReturn(Optional.empty());

	    // Act & Assert
	    assertThrows(ResourceNotFoundException.class,
	        () -> bookShopService.updateBook(1L, bookDTO));

	    verify(bookRepo, never()).save(any());
	}

	@Test
	void updateBook_whenPartialFieldsProvided_updatesOnlyGivenFields() {
	    // Arrange
	    when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
	    when(bookRepo.save(any(Book.class))).thenReturn(book);

	    bookDTO.setTitle("New Title");
	    bookDTO.setAuthor(null);

	    // Act
	    BookDTO result = bookShopService.updateBook(1L, bookDTO);

	    // Assert
	    assertEquals("New Title", result.getTitle());
	    assertEquals("John Isiah Sanchez", result.getAuthor()); // unchanged
	}

	/**
	 * Method: deleteBook
	 * Scenario: Book exists and is within allowed deletion period
	 * Expected: Book is successfully deleted
	 */
	@Test
	void deleteBook_whenValidDate_deletesSuccessfully() {
	    // Arrange
	    when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

	    // Act
	    bookShopService.deleteBook(1L);

	    // Assert
	    verify(bookRepo).delete(book);
	}

	/**
	 * Method: deleteBook
	 * Scenario: Book was created less than one week ago
	 * Expected: BookDeletionNotAllowedException is thrown
	 */
	@Test
	void deleteBook_whenWithinOneWeek_throwsException() {
	    // Arrange
	    book.setCreatedAt(LocalDate.now().minusDays(3));
	    when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

	    // Act & Assert
	    assertThrows(BookDeletionNotAllowedException.class,
	        () -> bookShopService.deleteBook(1L));

	    verify(bookRepo, never()).delete(any());
	}

	/**
	 * Method: deleteBook
	 * Scenario: Book was created more than one year ago
	 * Expected: BookDeletionNotAllowedException is thrown
	 */
	@Test
	void deleteBook_whenOlderThanOneYear_throwsException() {
	    // Arrange
	    book.setCreatedAt(LocalDate.now().minusYears(2));
	    when(bookRepo.findById(1L)).thenReturn(Optional.of(book));

	    // Act & Assert
	    assertThrows(BookDeletionNotAllowedException.class,
	        () -> bookShopService.deleteBook(1L));

	    verify(bookRepo, never()).delete(any());
	}
	/**
	 * Method: deleteBook
	 * Scenario: Book does not exists in the database
	 * Expected: ResourceNotFoundException is thrown
	 */
	@Test
	void deleteBook_whenBookNotFound_throwsResourceNotFoundException() {
	    // Arrange
	    when(bookRepo.findById(1L)).thenReturn(Optional.empty());

	    // Act & Assert
	    assertThrows(ResourceNotFoundException.class,
	        () -> bookShopService.deleteBook(1L));
	}
	
}
