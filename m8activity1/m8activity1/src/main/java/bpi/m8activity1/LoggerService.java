package bpi.m8activity1;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	//Activity 1
	public void log(String msg) {
		System.out.println(msg);
	}
}
