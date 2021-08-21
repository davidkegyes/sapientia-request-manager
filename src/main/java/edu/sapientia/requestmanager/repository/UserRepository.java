package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.Role;
import edu.sapientia.requestmanager.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByRole(Role role);
}
