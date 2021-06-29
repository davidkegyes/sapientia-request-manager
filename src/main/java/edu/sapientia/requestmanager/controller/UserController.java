package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.mapper.UserMapper;
import edu.sapientia.requestmanager.model.request.UserRequest;
import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import edu.sapientia.requestmanager.repository.UserRepository;
import edu.sapientia.requestmanager.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @GetMapping("/details")
    public ResponseEntity getUserDetails(Authentication authentication) {
        return ResponseEntity.ok(userMapper.mapToResponse((AuthorizedUser) authentication.getPrincipal()));
    }

    @GetMapping("/list")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userMapper.mapEntityToResponse(userRepository.save(userMapper.mapRequestToEntity(request))));
    }
}
