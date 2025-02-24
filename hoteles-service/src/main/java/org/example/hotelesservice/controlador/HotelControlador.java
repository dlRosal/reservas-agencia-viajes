package com.agencia.controlador;

import com.agencia.modelo.Hotel;
import com.agencia.servicio.HotelServicio;
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

    @GetMapping("/{id}")
    public Hotel obtenerHotel(@PathVariable Integer id) {
        return hotelServicio.obtenerHotelPorId(id);
    }

    @PostMapping
    public Hotel agregarHotel(@RequestBody Hotel hotel) {
        return hotelServicio.guardarHotel(hotel);
    }

    @DeleteMapping("/{id}")
    public void eliminarHotel(@PathVariable Integer id) {
        hotelServicio.eliminarHotel(id);
    }
}
