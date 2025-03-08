package org.example.authservice.service;

import org.example.authservice.model.Role;
import org.example.authservice.model.RoleType;
import org.example.authservice.model.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * Servicio de autenticación:
 * - Registro de usuario.
 * - Validación de credenciales (sin JWT).
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        // Verificar si el usuario ya existe en la base de datos
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Buscar o crear el rol USER
        Role defaultRole = roleRepository.findByName(RoleType.USER)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(RoleType.USER);
                    return roleRepository.save(newRole); // Guarda el rol en la BD si no existe
                });

        // Encriptar la contraseña antes de guardarla
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encripta antes de guardar

        // Asignar rol por defecto
        user.setRoles(Collections.singleton(defaultRole));

        // Guardar usuario en la base de datos
        return userRepository.save(user);
    }
}
