package m9.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import m9.project.dto.LoanDTO;
import m9.project.dto.UserDTO;
import m9.project.entity.User;
import m9.project.service.LoanService;
import m9.project.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	private final LoanService loanService;
	public UserController(UserService userService,
							LoanService loanService) {
		this.userService = userService;
		this.loanService = loanService;
	}
	
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public User createUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
	
	@GetMapping("/get")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/borrow")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<LoanDTO> borrowBook(@RequestBody LoanDTO loanDTO){
		LoanDTO borrowedBook = loanService.borowBook(loanDTO);
		return new ResponseEntity<>(borrowedBook, HttpStatus.CREATED);
	}
	
	@PostMapping("/return")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<LoanDTO> returnBook(@RequestParam Long loanId){
		LoanDTO returnBook = loanService.returnBook(loanId);
		return ResponseEntity.ok(returnBook);
	}
}

