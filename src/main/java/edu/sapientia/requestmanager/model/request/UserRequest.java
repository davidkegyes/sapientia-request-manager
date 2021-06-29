package edu.sapientia.requestmanager.model.request;

import edu.sapientia.requestmanager.repository.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    private Long id;
    private String neptunCode;
    private String email;
    private String firstname;
    private String lastname;
    @NotNull
    private Integer roleId;

}
