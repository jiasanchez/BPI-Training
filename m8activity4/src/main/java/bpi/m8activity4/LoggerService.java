package bpi.m8activity4;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	public void log(String msg) {
		System.out.println(msg);
	}
}
