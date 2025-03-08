package org.example.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("home")
    public String home() {
        return "Bienvenido al Home! Estás autenticado correctamente.";
    }
}
