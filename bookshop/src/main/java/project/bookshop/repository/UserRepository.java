package project.bookshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.bookshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByName(String name);
	 boolean existsByName(String name);
}
