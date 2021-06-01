package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
