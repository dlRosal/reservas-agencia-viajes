package org.example.vuelosservice.service;

import org.example.vuelosservice.model.Vuelo;
import org.example.vuelosservice.repository.VueloRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServicio {

    private final VueloRepositorio vueloRepositorio;

    public VueloServicio(VueloRepositorio vueloRepositorio) {
        this.vueloRepositorio = vueloRepositorio;
    }

    public List<Vuelo> listarVuelos() {
        return vueloRepositorio.findAll();
    }

    public Vuelo obtenerVueloPorId(Integer id) {
        return vueloRepositorio.findById(id).orElse(null);
    }

    public Vuelo guardarVuelo(Vuelo vuelo) {
        return vueloRepositorio.save(vuelo);
    }

    public void eliminarVuelo(Integer id) {
        vueloRepositorio.deleteById(id);
    }
}
