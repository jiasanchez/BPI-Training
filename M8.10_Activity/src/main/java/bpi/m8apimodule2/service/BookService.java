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
	
	public List<BookDTO> findAllBooks(){
			return books.stream()
					.map(book -> {
						BookDTO dto = new BookDTO();
						dto.setTitle(book.getTitle());
						dto.setAuthor(book.getAuthor());
						return dto;
					})
					.collect(Collectors.toList());
	}
}
