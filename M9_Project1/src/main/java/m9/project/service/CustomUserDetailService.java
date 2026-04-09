package m9.project.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import m9.project.entity.User;
import m9.project.repository.RoleRepository;
import m9.project.repository.UserRepository;


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
	@Transactional
   public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userRepo.findByName(name)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		String roles = user.getRole().getRole();
		System.out.println("Roles" + name + roles); 
		if (roles.isEmpty()) {
			System.out.println("Get Role" + user.getName() + roles);
			throw new UsernameNotFoundException("User has no roles assigned.");
		}
		List<SimpleGrantedAuthority> authorities = Collections.singletonList(
				new SimpleGrantedAuthority(roles));
				
		
		return new org.springframework.security.core.userdetails.User(
				user.getName(),
				user.getPassword(),
				user.getEnabled(),
				true,
				true,
				true,
				authorities
				);
   }
}
