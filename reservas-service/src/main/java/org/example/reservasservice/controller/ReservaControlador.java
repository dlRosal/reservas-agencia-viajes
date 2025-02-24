package org.example.reservasservice.controller;

import org.example.reservasservice.model.Reserva;
import org.example.reservasservice.service.ReservaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaControlador {

    private final ReservaServicio reservaServicio;

    public ReservaControlador(ReservaServicio reservaServicio) {
        this.reservaServicio = reservaServicio;
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaServicio.listarReservas();
    }

    @GetMapping("/{id}")
    public Reserva obtenerReserva(@PathVariable Integer id) {
        return reservaServicio.obtenerReservaPorId(id);
    }

    @PostMapping
    public Reserva agregarReserva(@RequestBody Reserva reserva) {
        return reservaServicio.guardarReserva(reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Integer id) {
        reservaServicio.eliminarReserva(id);
    }
}
