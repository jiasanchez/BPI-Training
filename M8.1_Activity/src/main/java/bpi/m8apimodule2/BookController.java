package bpi.m8apimodule2;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private List<Book> books = Arrays.asList(
			new Book(1L, "Milele", "James Caraan"),
			new Book(2L, "Avatar","James Cameron"),
			new Book(3L, "Interstellar", "Christopher Nolan")
			);
	//GET API
	@GetMapping
	public List<Book> getAllBooks(){
		return books;
	}
	
	//GET API by ID
	@GetMapping("/{id}")
	public Book getBookByID(@PathVariable Long id) {
		return books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
}
