package org.example.authservice.controller;

import org.example.authservice.model.User;
import org.example.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticación:
 * - Registro (`/auth/register`).
 * - Inicio de sesión (`/auth/login`).
 * - Obtener usuario autenticado (`/auth/user`).
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     * @param user Datos del usuario a registrar.
     * @return Usuario registrado con código 201.
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.status(201).body(authService.register(user));
    }

    /**
     * Confirma el inicio de sesión (Spring Security maneja la autenticación automáticamente).
     * @return Mensaje de inicio de sesión exitoso.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Inicio de sesión exitoso");
    }

    /**
     * Obtiene los detalles del usuario autenticado.
     * @return Nombre del usuario autenticado.
     */
    @GetMapping("/user")
    public ResponseEntity<String> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("Usuario autenticado: " + authentication.getName());
    }
}
