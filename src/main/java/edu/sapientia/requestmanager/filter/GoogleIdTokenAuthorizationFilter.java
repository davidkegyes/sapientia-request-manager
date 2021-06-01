package edu.sapientia.requestmanager.filter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import edu.sapientia.requestmanager.repository.entity.Role;
import edu.sapientia.requestmanager.repository.entity.User;
import edu.sapientia.requestmanager.service.RoleService;
import edu.sapientia.requestmanager.service.SecurityUserDetailsService;
import edu.sapientia.requestmanager.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Slf4j
@Data
@Component
public class GoogleIdTokenAuthorizationFilter extends OncePerRequestFilter {

    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    private final SecurityUserDetailsService userDetailsService;

    private final UserService userService;

    private final RoleService roleService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            GoogleIdToken idToken = GoogleIdToken.parse(googleIdTokenVerifier.getJsonFactory(), jwt);
            try {
                username = googleIdTokenVerifier.verify(idToken) ? idToken.getPayload().getEmail() : null;
            } catch (GeneralSecurityException e) {
                log.error("Token validation exception", e);
            }
            if (username != null && !userService.existsByEmail(username)){
                storeUser(idToken.getPayload());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void storeUser(GoogleIdToken.Payload payload) {
        User user = new User();
        user.setEmail(payload.getEmail());
        user.setFirstname((String) payload.get("given_name"));
        user.setLastname((String) payload.get("family_name"));
        Role role = getRole(user.getEmail());
        if (role == null){
            return;
        }
        user.setRoles(Collections.singleton(role));
        userService.saveUser(user);
    }

    private Role getRole(String email){
        if (email.toLowerCase().substring(email.indexOf("@") + 1).startsWith("student")){
            return roleService.findByName("student");
        }
        log.warn("Can not determine user role for email: " + email);
        return null;
    }


}
