package m9.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import m9.project.service.CustomUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService userDetailsService;
	
	@Autowired
	private CustomAuthEntryPoint customAuthEntryPoint;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
				http.csrf(csrf -> csrf.disable())
					.authenticationProvider(authenticationProvider())
					.authorizeHttpRequests(auth -> auth
					.requestMatchers("/library/books/**","/library/loans").hasAnyRole("ADMIN","USER")
					.requestMatchers("/library/add","/library/update/**","/library/delete/**").hasRole("ADMIN")
					.requestMatchers("/users/create", "/users/get").hasRole("ADMIN")
					.requestMatchers("/users/borrow", "/users/return").hasRole("USER")
					.anyRequest().authenticated()
					)
					.exceptionHandling(exception -> exception
							.authenticationEntryPoint(customAuthEntryPoint)
							.accessDeniedHandler(customAccessDeniedHandler))		
					.httpBasic(withDefaults());
			return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	   public PasswordEncoder passwordEncoder() {
	       return new BCryptPasswordEncoder();
	   }
}
