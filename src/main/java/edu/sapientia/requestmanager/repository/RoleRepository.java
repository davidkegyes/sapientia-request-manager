package edu.sapientia.requestmanager.repository;

import edu.sapientia.requestmanager.repository.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNameIgnoreCase(String name);
}
