package m9.project.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import m9.project.dto.UserDTO;
import m9.project.entity.Role;
import m9.project.entity.User;
import m9.project.exception.DuplicateResourceException;
import m9.project.exception.ResourceNotFoundException;
import m9.project.repository.RoleRepository;
import m9.project.repository.UserRepository;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
		
		private final UserRepository userRepo;
		private final RoleRepository roleRepo;
		private final PasswordEncoder passwordEncoder;
		public UserService(UserRepository userRepo,
							RoleRepository roleRepo,
							PasswordEncoder passwordEncoder) {
			this.userRepo = userRepo;
			this.roleRepo = roleRepo;
			this.passwordEncoder = passwordEncoder;
		}
		
		@Transactional
		public User createUser(UserDTO userDTO) {
			String currentUser = getUser();
			User user = new User();
			Role role = roleRepo.findByRole(userDTO.getRole())
					.orElseThrow(() -> new ResourceNotFoundException("Role not found."));
			if (userRepo.existsByName(userDTO.getName())) {
				logger.warn("User {} inputted Duplicate username", currentUser, userDTO.getName());
				throw new DuplicateResourceException("Username already exists.");
			}
			user.setUserID(userDTO.getUserID());
			user.setName(userDTO.getName());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setEnabled(userDTO.getEnabled());
			logger.info("User {} Successfully Created User {}", currentUser, user.getName());
			user.setRole(role);
			
			return userRepo.save(user);
		}
		
		public List<User> getAllUsers(){
			return userRepo.findAll();
		}
		
		//Get User
		private String getUser() {
			try {
				return org.springframework.security.core.context.SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getName();
			} catch (Exception e) {
				return "anonymous";
			}
		}
}
