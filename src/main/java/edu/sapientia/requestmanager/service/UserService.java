package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.entity.User;
import edu.sapientia.requestmanager.repository.UserRepository;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            // TODO Create custom exception and behaviour.
           throw new RuntimeException("User not found: " + email);
        }
        return user;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<String> getAllSecretaryEmailAddresses() {
        return userRepository.findAllByRole(roleService.findByName("SECRETARY")).stream().map(User::getEmail).collect(Collectors.toList());
    }
}
