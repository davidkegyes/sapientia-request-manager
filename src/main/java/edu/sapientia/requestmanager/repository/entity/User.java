package edu.sapientia.requestmanager.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String neptunCode;
    private String email;
    private String firstname;
    private String lastname;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
