package m8.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import m8.project.dto.BookDTO;
import m8.project.entity.Book;
import m8.project.repository.BookRepository;
import m8.project.repository.LoanRepository;
import m8.project.repository.UserRepository;

@Service
public class BookService {
	
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
			return bookRepo.findAll()
					.stream()
					.map(this::convertToDTO)
					.toList();
		}
		//GET BOOKS BY ID
		public BookDTO findById(Long id){
			Book book = bookRepo.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Book ID not found."));
			return convertToDTO(book);
		}
		//GET AVAILABLE BOOKS
		public List<BookDTO> getAvailableBooks() {
			List<Book> books = bookRepo.findByIsAvailableTrue();
			return books.stream()
					.map(this::convertToDTO)
					.toList();
		}
		//GET BORROWED BOOKS
		public List<BookDTO> getBorrowedBooks() {
			List<Book> books = bookRepo.findByIsAvailableFalse();
			return books.stream()
					.map(this::convertToDTO)
					.toList();
		}
		//ADD BOOK
		public BookDTO addBook(BookDTO bookDTO) {
			if (bookRepo.existsById(bookDTO.getId())) {
				throw new IllegalArgumentException("Book ID already exists.");
			}
			Book book = convertToEntity(bookDTO);
			Book savedBook = bookRepo.save(book);
			return convertToDTO(savedBook);
		}
		//UPDATE BOOK
		public BookDTO updateBook(Long id, BookDTO bookDTO) {
			Book existingBook = bookRepo.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("Book ID not found."));
			
			if (bookDTO.getTitle() != null && !bookDTO.getTitle().isBlank()) {
				existingBook.setTitle(bookDTO.getTitle());
			}
			if (bookDTO.getAuthor() != null && !bookDTO.getAuthor().isBlank()) {
				existingBook.setAuthor(bookDTO.getAuthor());
			}
			Book updatedBook = bookRepo.save(existingBook);
			return convertToDTO(updatedBook);
		}
		//DELETE BOOK
		@Transactional
		public void deleteBook(Long id) {
			Book book = bookRepo.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Book ID not found."));
			bookRepo.delete(book);
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
}
