package org.example.vuelosservice.service;

import org.example.vuelosservice.model.Vuelo;
import org.example.vuelosservice.repository.VueloRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServicio {

    private final VueloRepositorio vueloRepositorio;

    public VueloServicio(VueloRepositorio vueloRepositorio) {
        this.vueloRepositorio = vueloRepositorio;
    }

    public List<Vuelo> listarVuelos() {
        return vueloRepositorio.findAll();
    }

    // 🔥 Nuevo método para obtener un vuelo por ID
    public Optional<Vuelo> obtenerVueloPorId(Integer id) {
        return vueloRepositorio.findById(id);
    }

    public void actualizarPlazas(Integer id, int cantidad) {
        Vuelo vuelo = obtenerVueloPorId(id).orElseThrow(() -> new RuntimeException("Vuelo no encontrado con ID: " + id));
        if (vuelo.getAsientosDisponibles() < cantidad) {
            throw new RuntimeException("No hay suficientes asientos disponibles.");
        }
        vuelo.setAsientosDisponibles(vuelo.getAsientosDisponibles() - cantidad);
        vueloRepositorio.save(vuelo);
    }
}
