package edu.sapientia.requestmanager.mapper;

import edu.sapientia.requestmanager.model.response.UserDetailsResponse;
import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserToUserDetailsResponseMapper {

    UserDetailsResponse map(AuthorizedUser user);

    @AfterMapping
    default void mapPermissions(AuthorizedUser user, @MappingTarget UserDetailsResponse response) {
        response.setPermissions(user.getRoles().stream().flatMap(r -> r.getPermissions().stream()).map(p -> p.getName()).collect(Collectors.toSet()));
    }
}
