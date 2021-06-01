package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.repository.entity.User;
import edu.sapientia.requestmanager.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;

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
}
