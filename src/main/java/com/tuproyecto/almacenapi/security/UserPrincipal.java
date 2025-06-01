package com.tuproyecto.almacenapi.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tuproyecto.almacenapi.entity.Role;
import com.tuproyecto.almacenapi.entity.User;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password; // Retorna la contraseña encriptada
    }

    @Override
    public String getUsername() {
        return username; // Retorna el nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Aquí puedes agregar lógica real si quieres
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Aquí puedes agregar lógica real si quieres
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Aquí puedes agregar lógica real si quieres
    }

    @Override
    public boolean isEnabled() {
        return true; // Aquí puedes agregar lógica real si quieres
    }
}
