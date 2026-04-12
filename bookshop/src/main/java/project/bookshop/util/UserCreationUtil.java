package project.bookshop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import project.bookshop.entity.Role;
import project.bookshop.entity.User;
import project.bookshop.repository.RoleRepository;
import project.bookshop.repository.UserRepository;


@Configuration
public class UserCreationUtil {
	private static final Logger logger = LoggerFactory.getLogger(UserCreationUtil.class);
	@Bean
	CommandLineRunner initData(UserRepository userRepo,
							   RoleRepository roleRepo,
							   PasswordEncoder passwordEncoder) {
		return args -> {
			logger.info("Initializing User.");
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
			logger.info("User has been initialized. ID : {}, Name : {}", admin.getUserID(), admin.getName());
			}
		};
	}
}
