package m9.project.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import m9.project.entity.Role;
import m9.project.entity.User;
import m9.project.repository.RoleRepository;
import m9.project.repository.UserRepository;
	


@Configuration
public class InitializeUsers {
	@Bean
	CommandLineRunner initData(UserRepository userRepo,
							   RoleRepository roleRepo,
							   PasswordEncoder passwordEncoder) {
		return args -> {
			Role adminRole = roleRepo.findByRole("ROLE_ADMIN")
					.orElseGet(() -> roleRepo.save(new Role(null, "ROLE_ADMIN")));
			Role userRole = roleRepo.findByRole("ROLE_ADMIN")
					.orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));
			
			if (userRepo.findByName("admin").isEmpty()) {
				User admin = new User();
				admin.setName("admin");
				admin.setPassword(passwordEncoder.encode("adminpw"));
				admin.setEnabled(true);
				admin.setRole(adminRole);
				userRepo.save(admin);
			}
		};
	}

}
