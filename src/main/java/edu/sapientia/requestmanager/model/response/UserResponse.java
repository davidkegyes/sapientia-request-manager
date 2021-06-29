package edu.sapientia.requestmanager.model.response;

import edu.sapientia.requestmanager.model.request.RoleResponse;
import edu.sapientia.requestmanager.repository.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserResponse implements Serializable {

    private int id;
    private String neptunCode;
    private String email;
    private String firstname;
    private String lastname;
    private RoleResponse role;
}
