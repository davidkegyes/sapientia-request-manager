package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.request.UserRequest;
import edu.sapientia.requestmanager.model.response.UserDetailsResponse;
import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import edu.sapientia.requestmanager.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {RoleMapper.class})
public interface UserMapper {

    UserDetailsResponse mapToResponse(AuthorizedUser user);

    @Mapping(target = "role.id", source = "roleId")
    User mapRequestToEntity(UserRequest user);

    UserDetailsResponse mapEntityToResponse(User user);
}
