package m9.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m9.project.entity.Role;

public interface RoleRepository  extends JpaRepository<Role, Long> {
		Optional<Role> findByRole(String role);
}
