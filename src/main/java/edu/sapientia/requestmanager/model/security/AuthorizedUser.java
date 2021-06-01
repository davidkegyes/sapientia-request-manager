package edu.sapientia.requestmanager.model.security;

import edu.sapientia.requestmanager.repository.entity.Role;
import edu.sapientia.requestmanager.repository.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AuthorizedUser implements UserDetails {

    private Long id;
    private String neptunCode;
    private String email;
    private String firstname;
    private String lastname;
    private Set<Role> roles;

    public AuthorizedUser(User user) {
        this.id = user.getId();
        this.neptunCode = user.getNeptunCode();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.roles = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().flatMap(r -> r.getPermissions().stream()).map(p -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
