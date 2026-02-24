package bpi.m8apimodule2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private List<Book> books = new ArrayList<>();
	
	public BookController() {
			books.add(new Book(1L,"Milele", "James Caraan"));
			books.add(new Book(2L, "Avatar","James Cameron"));
			books.add(new Book(3L, "Interstellar", "Christopher Nolan"));
			
	}
	//M8.1 ACTIVITY
	@GetMapping
	public List<Book> getAllBooks(){
		return books;
	}
	
	//M8.1 ACTIVITY
/*	@GetMapping("/{id}")

	public Book getBookByID(@PathVariable Long id) {
		return books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
*/	
	//M8.2 ACTIVITY
	
	@PostMapping
	public Book addBook(@RequestBody Book book) {
		book.setId((long) (books.size() +1));
		books.add(book);
		
		return book;
	}
	
	//M8.3 ACTIVITY
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Long id) {
		return books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Book not found."));		
	}
	
	//M8.4 ACTIVITY
	@GetMapping("/search")
	public Book getBookByParamId(@RequestParam Long id) {
		return books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Book not found."));
	}
}
