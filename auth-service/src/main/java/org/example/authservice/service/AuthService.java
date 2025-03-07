package org.example.authservice.service;

import org.example.authservice.security.JwtUtil;
import org.example.authservice.model.User;
import org.example.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Servicio de autenticación:
 * - Registro de usuario.
 * - Validación de credenciales y generación de token.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encripta la contraseña
        return userRepository.save(user); // Guarda en la base de datos
    }

    public Map<String, String> login(User user) {
        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(user.getUsername()); // Genera JWT
        return Collections.singletonMap("token", token);
    }
}
