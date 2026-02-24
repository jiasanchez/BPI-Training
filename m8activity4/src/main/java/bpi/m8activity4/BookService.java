package bpi.m8activity4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	private LoggerService loggerService;
	
	public void createBook(String msg) {
		loggerService.log("Field Injection:" + msg);
	}
}
