package org.example.authservice.config;

import org.example.authservice.model.Role;
import org.example.authservice.model.RoleType;
import org.example.authservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(RoleType.ROLE_USER).isEmpty()) {
                roleRepository.save(new Role(null, RoleType.ROLE_USER));
            }
            if (roleRepository.findByName(RoleType.ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(null, RoleType.ROLE_ADMIN));
            }
        };
    }
}
