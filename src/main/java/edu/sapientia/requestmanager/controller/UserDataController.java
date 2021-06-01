package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.UserToUserDetailsResponseMapper;
import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import edu.sapientia.requestmanager.repository.UserRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@CrossOrigin(origins="*")
public class UserDataController {

    private final UserRepository userRepository;

    private final UserToUserDetailsResponseMapper userToUserDetailsResponseMapper;

    @GetMapping("/user/details")
    public ResponseEntity getUserDetails(Authentication authentication) {
        return ResponseEntity.ok(userToUserDetailsResponseMapper.map((AuthorizedUser) authentication.getPrincipal()));
    }
}
