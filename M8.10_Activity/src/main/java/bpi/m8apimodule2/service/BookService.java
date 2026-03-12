package bpi.m8apimodule2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import bpi.m8apimodule2.dto.BookDTO;
import bpi.m8apimodule2.model.Book;
import bpi.m8apimodule2.repository.BookRepository;

@Service
public class BookService {

		private final BookRepository bookRepo;
		List<Book> books = new ArrayList<>();
	public BookService(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	public List<Book> findAllBooks(){
			return bookRepo.findAll();
	}
	public BookDTO addBook(BookDTO bookDTO) {
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		Book save = bookRepo.save(book);
		
		BookDTO response = new BookDTO();
		response.setTitle(save.getTitle());
		response.setAuthor(save.getAuthor());
		return response;
	}
	public Book updateMovie(Long id, Book update) {
		Book uBook = bookRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Movie not found."));
		if(update.getTitle() != null) {
			uBook.setTitle(update.getTitle());
		}		
		if(update.getAuthor() != null) {
			uBook.setAuthor(update.getAuthor());
		}
		return bookRepo.save(uBook);
	}
	public void deleteBook(Long id) {
		if (!bookRepo.existsById(id)) {
			throw new RuntimeException("Book is not existing with " + id);
		}
		bookRepo.deleteById(id);
	}
}
