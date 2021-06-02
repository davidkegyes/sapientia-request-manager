package edu.sapientia.requestmanager.config;

import edu.sapientia.requestmanager.filter.GoogleIdTokenAuthorizationFilter;
import edu.sapientia.requestmanager.service.SecurityUserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final SecurityUserDetailsService userDetailsService;

    private final GoogleIdTokenAuthorizationFilter googleIdTokenAuthorizationFilter;

    public SecurityConfig(SecurityUserDetailsService userDetailsService, GoogleIdTokenAuthorizationFilter googleIdTokenAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.googleIdTokenAuthorizationFilter = googleIdTokenAuthorizationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/test/**", "/swagger-ui.html", "/swagger-ui/**","/v3/api-docs/**").permitAll().anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(googleIdTokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "HEAD", "POST", "PUT", "OPTIONS", "DELETE");
    }
}