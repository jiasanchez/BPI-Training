package m9.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import m9.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByName(String name);
	 boolean existsByName(String name);
}
