package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.request.RoleResponse;
import edu.sapientia.requestmanager.repository.entity.Permission;
import edu.sapientia.requestmanager.repository.entity.Role;
import org.mapstruct.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    RoleResponse mapToResponse(Role role);

    @AfterMapping
    default void mapPermissionObjectListToStringList(Role role, @MappingTarget RoleResponse response) {
        try {
            response.setPermissions(role.getPermissions().stream().map(Permission::getName).collect(Collectors.toList()));
        } catch (Exception e) {
            response.setPermissions(Collections.EMPTY_LIST);
        }
    }
}
