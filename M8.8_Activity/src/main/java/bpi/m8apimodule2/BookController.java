package bpi.m8apimodule2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
	
	private BookDTO toDTO(Book book) {
		return new BookDTO(book.getTitle(), book.getAuthor());
	}
	
	private Book fromDTO(BookDTO bookDTO) {
		return new Book(bookDTO.getTitle(), bookDTO.getAuthor());
	}
	
	@GetMapping
	public List<BookDTO> getAllBooks(){
		List<BookDTO> DTO = new ArrayList<>();
		for (Book book : books) {
			DTO.add(toDTO(book));
		}
		return DTO;
	}

	@PostMapping
	public BookDTO addBook(@RequestBody BookDTO bookDTO) {
		Long newId = (long) (books.size() + 1);
		Book book = fromDTO(bookDTO);
		book.setId(newId);
		books.add(book);
		return toDTO(book);
	}
	
	//M8.3 ACTIVITY
	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
		return books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst()
				.map(book -> ResponseEntity.ok(toDTO(book)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//M8.4 ACTIVITY
	@GetMapping("/search")
	public ResponseEntity<BookDTO> getBookByParamId(@RequestParam Long id) {
		return books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst()
				.map(book -> ResponseEntity.ok(toDTO(book)))
				.orElse(ResponseEntity.notFound().build());
	}
}
