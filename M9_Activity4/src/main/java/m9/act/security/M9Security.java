package m9.act.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class M9Security {

/*	@Bean
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
*/
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/dashboard/**").hasAnyRole("USER", "MANAGER")
						.requestMatchers("/home/**").hasRole("USER")
						.requestMatchers("/reports/**").hasRole("MANAGER")
						.anyRequest().authenticated()
				)
				.httpBasic(withDefaults());
			return http.build();
				
	}
	@Bean
	   public InMemoryUserDetailsManager userDetailsService() {
	       UserDetails user1 = User.withUsername("dev_1")
	                              .password(passwordEncoder().encode("dev1pw"))
	                              .roles("USER")
	                              .build();
	       UserDetails user2 = User.withUsername("dev_2")
                   .password(passwordEncoder().encode("dev2pw"))
                   .roles("USER")
                   .build();	
	       UserDetails admin = User.withUsername("mgr_1")
                   .password(passwordEncoder().encode("mgr_pw"))
                   .roles("MANAGER")
                   .build();
	       return new InMemoryUserDetailsManager(user1, user2, admin);
	   }
	@Bean
	   public PasswordEncoder passwordEncoder() {
	       return new BCryptPasswordEncoder();
	   }

}
