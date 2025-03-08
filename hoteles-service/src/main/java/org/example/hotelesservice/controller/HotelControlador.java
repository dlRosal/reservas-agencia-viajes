package org.example.hotelesservice.controller;

import org.example.hotelesservice.model.Hotel;
import org.example.hotelesservice.service.HotelServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelControlador {

    private final HotelServicio hotelServicio;

    public HotelControlador(HotelServicio hotelServicio) {
        this.hotelServicio = hotelServicio;
    }

    @GetMapping
    public List<Hotel> listarHoteles() {
        return hotelServicio.listarHoteles();
    }

    // Metodo para obtener hotel por ID
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerHotelPorId(@PathVariable Integer id) {
        return hotelServicio.obtenerHotelPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
