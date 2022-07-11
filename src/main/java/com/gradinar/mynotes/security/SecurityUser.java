package com.gradinar.mynotes.security;

import com.gradinar.mynotes.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {

    private final String email;
    private final String password;
    private final List<Role> roles;
    private boolean isValid;

    public SecurityUser(String email, String password, boolean isValid, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.isValid = isValid;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isValid;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isValid;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isValid;
    }

    @Override
    public boolean isEnabled() {
        return isValid;
    }
}
