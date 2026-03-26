package m9.act.security;


import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import m9.act.security.service.CustomUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class M9Security {
	
	@Autowired
	private CustomUserDetailService userDetailsService;
	
	@Autowired
	private CustomAuthEntryPoint customAuthEntryPoint;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/profile/**").authenticated()
						.requestMatchers("/dashboard/**").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/home/**").hasRole("USER")
						.requestMatchers("/reports/**").hasRole("ADMIN")
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
