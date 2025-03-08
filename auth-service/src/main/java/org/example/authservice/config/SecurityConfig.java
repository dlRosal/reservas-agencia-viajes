package org.example.authservice.config;

import org.example.authservice.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad con Spring Security sin JWT:
 * - API REST sin sesiones, autenticación con credenciales.
 * - Se eliminan los formularios de login.
 * - Se permite acceso público a `/auth/register` y `/auth/login`.
 */
@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        .requestMatchers("/home").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/auth/login") // Procesa el login aquí
                        .successHandler((request, response, authentication) -> {
                            response.setContentType("application/json");
                            response.getWriter().write("{\"message\": \"Inicio de sesión exitoso\"}");
                        }) // Evita redirecciones al login y devuelve JSON
                        .failureHandler((request, response, exception) -> {
                            response.setContentType("application/json");
                            response.setStatus(401);
                            response.getWriter().write("{\"error\": \"Credenciales incorrectas\"}");
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setContentType("application/json");
                            response.getWriter().write("{\"message\": \"Sesión cerrada exitosamente\"}");
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .build();
    }




    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
