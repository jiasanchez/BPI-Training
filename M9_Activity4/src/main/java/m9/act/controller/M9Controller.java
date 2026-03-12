package m9.act.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class M9Controller {

		@GetMapping("/dashboard")
		public String dashboard() {
			return "Welcome to the portal";
		}
		
		@GetMapping("/home")
		public String user() {
			return "User Dashboard";
		}
		@GetMapping("/reports")
		public String admin() {
			return "Manager reports";
		}
}
