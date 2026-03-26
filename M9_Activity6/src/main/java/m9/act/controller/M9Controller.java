package m9.act.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class M9Controller {

		@GetMapping("/dashboard")
		public String dashboard() {
			return "Welcome to the portal";
		}
		
		@GetMapping("/home")
		@PreAuthorize("hasRole('USER')")
		public String user() {
			return "User Dashboard";
		}
		@GetMapping("/reports")
		@PreAuthorize("hasRole('ADMIN')")
		public String admin() {
			return "Manager reports";
		}
		
		@GetMapping("/profile/username/{username}")
		@PreAuthorize("#username == authentication.name")
		public String getProfileByUsername(@PathVariable String username) {
		    return "Profile of " + username;
		}
		
		@GetMapping("/whoami")
		public String whoami(Authentication auth) {
			return auth.getName();
		}

}
