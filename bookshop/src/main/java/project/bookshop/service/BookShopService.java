/**
 * Service class that handles business logic for managing books.
 *
 * <p>This service provides CRUD operations with additional business rules such as:
 * <ul>
 *     <li>Restricting deletion of books within 1 week of creation</li>
 *     <li>Preventing deletion of books older than 1 year</li>
 * </ul>
 *
 * It interacts with {@code BookRepository} for persistence operations
 * and converts between {@code Book} and {@code BookDTO}.
 */

package project.bookshop.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import project.bookshop.dto.BookDTO;
import project.bookshop.entity.Book;
import project.bookshop.exception.BookDeletionNotAllowedException;
import project.bookshop.exception.DuplicateResourceException;
import project.bookshop.exception.ResourceNotFoundException;
import project.bookshop.repository.BookRepository;
import project.bookshop.repository.UserRepository;


@Service
public class BookShopService {
	private static final Logger logger = LoggerFactory.getLogger(BookShopService.class);
	private final BookRepository bookRepo;
	private final UserRepository userRepo;
	
	public BookShopService(BookRepository bookRepo,
							UserRepository userRepo) {
		this.bookRepo = bookRepo;
		this.userRepo = userRepo;
	}
	
	//1. READ A BOOK
	/**
	 * Retrieves all books from the database.
	 *
	 * @return a list of {@link BookDTO} representing all books
	 */
	public List<BookDTO> getAllBooks(){
		String user = getUser();
		logger.info("User {} Succesfully Extracted All Books.", user);
		return bookRepo.findAll()
				.stream()
				.map(this::convertToDTO)
				.toList();
	}
	
	//2. CREATE A BOOK
	/**
	 * Adds a new book to the system.
	 *
	 * <p>The method converts the incoming DTO to an entity,
	 * saves it in the database, and returns the saved entity as a DTO.
	 *
	 * @param bookDTO the book details to be added
	 * @return the saved {@link BookDTO} with generated values (Book ID)
	 */
	public BookDTO addBook(BookDTO bookDTO) {
		String user = getUser();	
		Book book = convertToEntity(bookDTO);
		Book savedBook = bookRepo.save(book);
		logger.info("User {} successfully addedd Book ID {}.", user, book.getId());
		return convertToDTO(savedBook);
	}
	
	//3. UPDATE A BOOK
	/**
	 * Updates an existing book by its ID.
	 *
	 * <p>If the book is not found, a {@link ResourceNotFoundException} is thrown.
	 * Only non-null fields from the provided DTO are used to update the entity.
	 *
	 * @param id the ID of the book to update
	 * @param bookDTO the updated book data
	 * @return the updated {@link BookDTO}
	 * @throws ResourceNotFoundException if no book exists with the given ID
	 */
	public BookDTO updateBook(Long id, BookDTO bookDTO) {
		String user = getUser();
		Book existingBook = bookRepo.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Book ID not found."));
		
		if (bookDTO.getTitle() != null && !bookDTO.getTitle().isBlank()) {
			existingBook.setTitle(bookDTO.getTitle());
		}
		if (bookDTO.getAuthor() != null && !bookDTO.getAuthor().isBlank()) {
			existingBook.setAuthor(bookDTO.getAuthor());
		}
		if (bookDTO.getPrice() != null) {
			existingBook.setPrice(bookDTO.getPrice());
		}
		if (bookDTO.getCreatedAt() != null ) {
			existingBook.setCreatedAt(bookDTO.getCreatedAt());
		}
		Book updatedBook = bookRepo.save(existingBook);
		logger.info("User {} Succesfully updated Book. Details: Title: [{}] Author:[{}]", 
						user, existingBook.getTitle(), existingBook.getAuthor());
		return convertToDTO(updatedBook);
	}
	
	//4. DELETE A BOOK
	/**
	 * Deletes a book by its ID if it satisfies business rules.
	 *
	 * <p>Business rules:
	 * <ul>
	 *     <li>Book cannot be deleted within 1 week of creation</li>
	 *     <li>Book cannot be deleted if older than 1 year</li>
	 * </ul>
	 *
	 * @param id the ID of the book to delete
	 * @throws ResourceNotFoundException if the book is not found
	 * @throws BookDeletionNotAllowedException if business rules are violated
	 */
	public void deleteBook(Long id) {
		String user = getUser();
		Book book = bookRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Book ID not found."));
		LocalDate createdAt = book.getCreatedAt();
		LocalDate now = LocalDate.now();
		
		//Condition 1: Creation within 1 week
		if (now.isBefore(createdAt.plusWeeks(1))) {
			logger.warn("User {} cannot delete Book ID {} created within 1 week.", user, book.getId());
			throw new BookDeletionNotAllowedException("Book cannot be deleted within 1 Week of Creation");			
		}
		//Condition 2: Books created older than 1 year
		if (now.isAfter(createdAt.plusYears(1))){
			logger.warn("User {} cannot delete Book ID {} older than 1 year.", user, book.getId());
			throw new BookDeletionNotAllowedException("Book cannot be deleted after 1 Year of Creation");
		}
		bookRepo.delete(book);
		logger.info("User {} Succesfully Deleted Book ID {}", user, book.getId());
	}
	
	//ENTITY TO DTO
	private BookDTO convertToDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setAuthor(book.getAuthor());
		bookDTO.setPrice(book.getPrice());
		bookDTO.setCreatedAt(book.getCreatedAt());
		return bookDTO;
	}
	
	//DTO TO ENTITY
	private Book convertToEntity(BookDTO bookDTO) {
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setPrice(bookDTO.getPrice());
		book.setCreatedAt(bookDTO.getCreatedAt());
		return book;
	}
	
	//Get UserID
	private String getUser() {
		try {
			return org.springframework.security.core.context.SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getName();
		} catch (Exception e) {
			return "anonymous";
		}
	}
}
