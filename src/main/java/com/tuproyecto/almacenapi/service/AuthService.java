package com.tuproyecto.almacenapi.service;

import com.tuproyecto.almacenapi.dto.LoginRequest;
import com.tuproyecto.almacenapi.dto.LoginResponse;
import com.tuproyecto.almacenapi.entity.Role;
import com.tuproyecto.almacenapi.entity.User;
import com.tuproyecto.almacenapi.repository.RoleRepository;
import com.tuproyecto.almacenapi.repository.UserRepository;
import com.tuproyecto.almacenapi.security.JwtUtil;
import com.tuproyecto.almacenapi.security.UserPrincipal;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tuproyecto.almacenapi.dto.RegisterRequest;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Crear usuario admin y user en memoria para pruebas
    @PostConstruct
    public void initRolesAndUsers() {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN").get()));
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(Set.of(roleRepository.findByName("ROLE_USER").get()));
            userRepository.save(user);
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userPrincipal);

            return new LoginResponse(token);

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales inválidas: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error en login: " + e.getMessage());
        }
    }

    public void register(RegisterRequest request) {
        // Verificar si el usuario ya existe
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodePassword(request.getPassword())); // Aquí codificas la contraseña
        user.setEmail(request.getEmail());

        // Otros campos...

        userRepository.save(user);
    }

    private String encodePassword(String rawPassword) {
        // Puedes usar BCryptPasswordEncoder o lo que prefieras
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

}
