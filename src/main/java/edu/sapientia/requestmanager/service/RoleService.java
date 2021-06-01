package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.entity.Role;
import edu.sapientia.requestmanager.repository.RoleRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByNameIgnoreCase(name);
    }

}
