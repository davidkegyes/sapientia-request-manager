package edu.sapientia.requestmanager.controller;

import edu.sapientia.requestmanager.repository.RoleRepository;
import edu.sapientia.requestmanager.repository.entity.Role;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(roleRepository.findAll());
    }

}
