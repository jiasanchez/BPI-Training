package bpi.m8activity1;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private final LoggerService loggerService;
	//Activity 1
	public BookService(LoggerService loggerService) { 
		this.loggerService = loggerService;
	}
	//Activity 1
	public void createBook(String msg) {
		loggerService.log("Log Created: " + msg);
	}

}
