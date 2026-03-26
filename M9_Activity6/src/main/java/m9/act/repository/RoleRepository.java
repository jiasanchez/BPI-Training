package m9.act.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import m9.act.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   @Query("SELECT r.role FROM Role r WHERE r.user.id = :userId")
   List<String> findRolesByUserId(@Param("userId") Long userId);
}

