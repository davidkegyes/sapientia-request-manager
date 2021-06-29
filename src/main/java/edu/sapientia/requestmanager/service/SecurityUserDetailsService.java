package edu.sapientia.requestmanager.service;

import edu.sapientia.requestmanager.model.security.AuthorizedUser;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Data
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userService.findByEmail(email);
        return new AuthorizedUser(userService.findByEmail(email));
//        return new User(user.getEmail(), "", getAuthorities(user.getRoles()));
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        return roles.stream().flatMap(r -> r.getPermissions().stream()).mapToResponse(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
//    }


}
