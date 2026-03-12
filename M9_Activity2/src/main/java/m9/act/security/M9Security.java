package m9.act.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class M9Security {

	@Bean
	   public InMemoryUserDetailsManager userDetailsService() {
	       UserDetails user = User.withUsername("zak-user")
	                              .password("{noop}password")
	                              .roles("USER")
	                              .build();
	       UserDetails admin = User.withUsername("zak-admin")
	                               .password("{noop}password")
	                               .roles("ADMIN")
	                               .build();
	       return new InMemoryUserDetailsManager(user, admin);
	   }

}
