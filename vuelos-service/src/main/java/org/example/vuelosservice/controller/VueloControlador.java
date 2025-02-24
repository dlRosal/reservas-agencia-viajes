package org.example.vuelosservice.controller;

import org.example.vuelosservice.model.Vuelo;
import org.example.vuelosservice.service.VueloServicio;
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

    @GetMapping("/{id}")
    public Vuelo obtenerVuelo(@PathVariable Integer id) {
        return vueloServicio.obtenerVueloPorId(id);
    }

    @PostMapping
    public Vuelo agregarVuelo(@RequestBody Vuelo vuelo) {
        return vueloServicio.guardarVuelo(vuelo);
    }

    @DeleteMapping("/{id}")
    public void eliminarVuelo(@PathVariable Integer id) {
        vueloServicio.eliminarVuelo(id);
    }
}
