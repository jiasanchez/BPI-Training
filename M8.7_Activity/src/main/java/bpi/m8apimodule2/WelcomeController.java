package bpi.m8apimodule2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	//PROFILE SETUP
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	@GetMapping("/api/welcome")
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
}
