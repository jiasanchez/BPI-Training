package m8.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import m8.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
