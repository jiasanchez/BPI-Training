package m9.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import m9.project.dto.BookDTO;
import m9.project.entity.Book;
import m9.project.exception.DuplicateResourceException;
import m9.project.exception.ResourceNotFoundException;
import m9.project.repository.BookRepository;
import m9.project.repository.LoanRepository;
import m9.project.repository.UserRepository;

@Service
public class BookService {
		private static final Logger logger = LoggerFactory.getLogger(BookService.class);
		private final BookRepository bookRepo;
		private final UserRepository userRepo;
		private final LoanRepository loanRepo;
		
		public BookService(BookRepository bookRepo,
							  UserRepository userRepo,
							  LoanRepository loanRepo) {
			this.bookRepo = bookRepo;
			this.loanRepo = loanRepo;
			this.userRepo = userRepo;
			
		}
		
		//GET ALL BOOKS
		public List<BookDTO> getAllBooks() {
			String user = getUser();
			logger.info("User {} Succesfully Extracted All Books.", user);
			return bookRepo.findAll()
					.stream()
					.map(this::convertToDTO)
					.toList();
		}
		//GET BOOKS BY ID
		public BookDTO findById(Long id){
			String user = getUser();			
			Book book = bookRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Book ID not found."));
			logger.info("User {} Succesfully Extracted book.", user);
			return convertToDTO(book);
		}
		//GET AVAILABLE BOOKS
		public List<BookDTO> getAvailableBooks() {
			String user = getUser();
			logger.info("User {} Succesfully Extracted Available Books.", user);
			List<Book> books = bookRepo.findByIsAvailableTrue();
			return books.stream()
					.map(this::convertToDTO)
					.toList();
		}
		//GET BORROWED BOOKS
		public List<BookDTO> getBorrowedBooks() {
			String user = getUser();
			logger.info("User {} Succesfully Extracted Borrowed Books.", user);
			List<Book> books = bookRepo.findByIsAvailableFalse();
			return books.stream()
					.map(this::convertToDTO)
					.toList();
		}
		//ADD BOOK
		public BookDTO addBook(BookDTO bookDTO) {
			String user = getUser();
			if (bookRepo.existsById(bookDTO.getId())) {
				logger.warn("User {} inputted duplicate book ID {}.", user, bookDTO.getId());
				throw new DuplicateResourceException("Book ID already exists.");
			}
			logger.info("User {} successfully addedd Book ID {}.", user, bookDTO.getId());
			Book book = convertToEntity(bookDTO);
			Book savedBook = bookRepo.save(book);
			return convertToDTO(savedBook);
		}
		//UPDATE BOOK
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
			Book updatedBook = bookRepo.save(existingBook);
			logger.info("User {} Succesfully updated Book. Details: Title: [{}] Author:[{}]", user, existingBook.getTitle(), existingBook.getAuthor());
			return convertToDTO(updatedBook);
		}
		//DELETE BOOK
		@Transactional
		public void deleteBook(Long id) {
			String user = getUser();
			Book book = bookRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Book ID not found."));
			bookRepo.delete(book);
			logger.info("User {} Succesfully Deleted Book ID {}", user, book.getId());
		}
		//ENTITY TO DTO
		private BookDTO convertToDTO(Book book) {
			BookDTO bookDTO = new BookDTO();
			bookDTO.setId(book.getId());
			bookDTO.setTitle(book.getTitle());
			bookDTO.setAuthor(book.getAuthor());
			bookDTO.setIsAvailable(book.getIsAvailable());
			return bookDTO;
		}
		
		//DTO TO ENTITY
		private Book convertToEntity(BookDTO bookDTO) {
			Book book = new Book();
			book.setId(bookDTO.getId());
			book.setTitle(bookDTO.getTitle());
			book.setAuthor(bookDTO.getAuthor());
			book.setIsAvailable(bookDTO.getIsAvailable());
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
