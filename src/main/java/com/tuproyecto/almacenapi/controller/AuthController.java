package com.tuproyecto.almacenapi.controller;

import com.tuproyecto.almacenapi.dto.RegisterRequest;
import com.tuproyecto.almacenapi.dto.LoginRequest;
import com.tuproyecto.almacenapi.dto.LoginResponse;
import com.tuproyecto.almacenapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

}
