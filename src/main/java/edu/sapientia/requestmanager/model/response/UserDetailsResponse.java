package edu.sapientia.requestmanager.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserDetailsResponse implements Serializable {

    private String neptunCode;
    private String email;
    private String firstname;
    private String lastname;
    private Set<String> permissions;

}
