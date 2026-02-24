package bpi.m8activity3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private LoggerService loggerService;
	
	public BookService() {}
	
	@Autowired
	public void setLoggerService(LoggerService loggerService) {
		this.loggerService = loggerService;
	}
	
	public void createBook(String msg) {
		loggerService.log("Setter Injection:" + msg);
	}
}
