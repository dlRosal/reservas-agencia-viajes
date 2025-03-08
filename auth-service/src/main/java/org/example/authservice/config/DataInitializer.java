package org.example.authservice.config;

import org.example.authservice.model.Role;
import org.example.authservice.model.RoleType;
import org.example.authservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para inicializar datos en la base de datos al iniciar la aplicación.
 */
@Configuration  // Indica que esta clase es una configuración de Spring.
public class DataInitializer {

    /**
     * Bean que se ejecuta al iniciar la aplicación y se encarga de insertar roles en la base de datos si no existen.
     * @param roleRepository Repositorio de roles para interactuar con la base de datos.
     * @return CommandLineRunner que ejecuta la inserción de roles al arrancar la aplicación.
     */
    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            // Verifica si el rol "ROLE_USER" ya existe en la base de datos, si no, lo crea.
            if (roleRepository.findByName(RoleType.ROLE_USER).isEmpty()) {
                roleRepository.save(new Role(null, RoleType.ROLE_USER)); // Guarda el rol en la BD.
            }

            // Verifica si el rol "ROLE_ADMIN" ya existe en la base de datos, si no, lo crea.
            if (roleRepository.findByName(RoleType.ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(null, RoleType.ROLE_ADMIN)); // Guarda el rol en la BD.
            }
        };
    }
}
