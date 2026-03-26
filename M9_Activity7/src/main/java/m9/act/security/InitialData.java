package m9.act.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import m9.act.model.Role;
import m9.act.model.User;
import m9.act.repository.RoleRepository;
import m9.act.repository.UserRepository;

@Configuration
public class InitialData {

		@Bean
		CommandLineRunner initData(UserRepository userRepo,
								   RoleRepository roleRepo,
								   PasswordEncoder passwordEncoder) {
			return args -> {
				User user = new User();
				user.setUsername("dev1");
				user.setPassword(passwordEncoder.encode("dev1pw"));
				user.setEnabled(true);
				
				userRepo.save(user);
				
				User user2 = new User();
				user2.setUsername("admin");
				user2.setPassword(passwordEncoder.encode("adminpw"));
				user2.setEnabled(true);
				
				userRepo.save(user2);	
				
				Role role = new Role();
				role.setUser(user);
				role.setRole("ROLE_USER");
				
				roleRepo.save(role);
				
				Role role2 = new Role();
				role2.setUser(user2);
				role2.setRole("ROLE_ADMIN");
				
				roleRepo.save(role2);
			};
		}
}
