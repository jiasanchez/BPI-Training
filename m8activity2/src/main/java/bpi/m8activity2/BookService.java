package bpi.m8activity2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private final LoggerService loggerService;
	
	public BookService(LoggerService loggerService) { 
		this.loggerService = loggerService;
	}
	
	public void createBook(String msg) {
		loggerService.log("Constructor Based:" + msg);
	}
}
