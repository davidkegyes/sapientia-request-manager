package edu.sapientia.requestmanager.model.response;

import edu.sapientia.requestmanager.model.request.RoleResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoResponse implements Serializable {

    private int id;
    private String neptunCode;
    private String email;
    private String firstname;
    private String lastname;
}
