package m8.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import m8.project.dto.UserDTO;
import m8.project.entity.User;
import m8.project.repository.UserRepository;

@Service
public class UserService {

		private final UserRepository userRepo;
		
		public UserService(UserRepository userRepo) {
			this.userRepo = userRepo;
		}
		
		public User createUser(UserDTO userDTO) {
			User user = new User();
			user.setUserID(userDTO.getUserID());
			user.setName(userDTO.getName());
			
			return userRepo.save(user);
		}
		
		public List<User> getAllUsers(){
			return userRepo.findAll();
		}
}
