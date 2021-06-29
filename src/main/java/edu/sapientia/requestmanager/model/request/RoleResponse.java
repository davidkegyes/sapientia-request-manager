package edu.sapientia.requestmanager.model.request;

import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {
    private String name;
    private List<String> permissions;
}
