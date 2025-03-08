package org.example.reservasservice.controller;

import org.example.reservasservice.model.Reserva;
import org.example.reservasservice.service.ReservaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin("*")  // Permite peticiones desde cualquier origen
@Validated
public class ReservaControlador {

    private static final Logger logger = LoggerFactory.getLogger(ReservaControlador.class);
    private final ReservaServicio reservaServicio;

    public ReservaControlador(ReservaServicio reservaServicio) {
        this.reservaServicio = reservaServicio;
    }

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        try {
            List<Reserva> reservas = reservaServicio.listarReservas();
            return ResponseEntity.ok(reservas);
        } catch (RuntimeException e) {
            logger.error("Error al obtener reservas: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Obtener reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReserva(@PathVariable Integer id) {
        try {
            Reserva reserva = reservaServicio.obtenerReservaPorId(id);
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException e) {
            logger.error("Reserva no encontrada con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(404).body("Reserva no encontrada con ID: " + id);
        }
    }

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<?> agregarReserva( @RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaServicio.guardarReserva(reserva);
            return ResponseEntity.ok(nuevaReserva);
        } catch (RuntimeException e) {
            logger.error("Error al guardar la reserva: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error al crear la reserva: " + e.getMessage());
        }
    }
}
