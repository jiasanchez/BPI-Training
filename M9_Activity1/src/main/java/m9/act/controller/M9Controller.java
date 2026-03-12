package m9.act.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class M9Controller {

		@GetMapping("/dashboard")
		public String dashboard() {
			return "Welcome my friend";
		}
}
