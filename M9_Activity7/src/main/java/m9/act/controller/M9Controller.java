package m9.act.controller;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
		public String profile(@AuthenticationPrincipal UserDetails user) {
			return "Hello, " + user.getUsername();
			}
		
		@GetMapping("/reports")
		@PreAuthorize("hasRole('ADMIN')")
		public String admin() {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null && auth.isAuthenticated()) {
			String username = auth.getName();
			System.out.println("Username : " + username);
			
			Object principal = auth.getPrincipal();
			System.out.println("Principal : " + principal);
			
			Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
			
			}
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
