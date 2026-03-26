package m9.act.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import m9.act.model.User;
import m9.act.repository.RoleRepository;
import m9.act.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	
	public CustomUserDetailService(UserRepository userRepo, 
									RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}
	
	@Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		List<String> roles = roleRepo.findRolesByUserId(user.getId());
		
		List<SimpleGrantedAuthority> authorities = roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.getEnabled(),
				true,
				true,
				true,
				authorities
				);
   }
		
}

