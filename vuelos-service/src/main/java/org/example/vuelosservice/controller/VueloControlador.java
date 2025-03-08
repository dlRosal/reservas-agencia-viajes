package org.example.vuelosservice.controller;

import org.example.vuelosservice.model.Vuelo;
import org.example.vuelosservice.service.VueloServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloControlador {

    private final VueloServicio vueloServicio;

    public VueloControlador(VueloServicio vueloServicio) {
        this.vueloServicio = vueloServicio;
    }

    @GetMapping
    public List<Vuelo> listarVuelos() {
        return vueloServicio.listarVuelos();
    }

    //metodo para obtener un vuelo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> obtenerVueloPorId(@PathVariable Integer id) {
        return vueloServicio.obtenerVueloPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //metodo para actualizar las plazas
    @PutMapping("/{id}/actualizarPlazas/{cantidad}")
    public ResponseEntity<String> actualizarPlazas(@PathVariable Integer id, @PathVariable int cantidad) {
        try {
            vueloServicio.actualizarPlazas(id, cantidad);
            return ResponseEntity.ok("Plazas actualizadas correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
