package bpi.m8apimodule2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/books")
public class BookController {
	
	private List<Book> books = new ArrayList<>();
	
	public BookController() {
			books.add(new Book(1L,"Milele", "James Caraan"));
			books.add(new Book(2L, "Avatar","James Cameron"));
			books.add(new Book(3L, "Interstellar", "Christopher Nolan"));
			
	}
	//M8.5 ACTIVITY
	@GetMapping
	@ResponseBody
	public List<Book> getAllBooks(){
		return books;
	}

}
