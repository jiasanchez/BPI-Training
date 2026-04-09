package m8.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import m8.project.dto.UserDTO;
import m8.project.entity.User;
import m8.project.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public User createUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
	
	@GetMapping
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
}

