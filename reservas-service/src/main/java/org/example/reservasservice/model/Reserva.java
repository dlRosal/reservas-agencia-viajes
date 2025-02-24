package org.example.reservasservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cliente;
    private Integer idHotel;
    private Integer idVuelo;
    private LocalDateTime fechaReserva;
    private double total;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @PrePersist
    public void prePersist() {
        this.fechaReserva = LocalDateTime.now();
        this.estado = EstadoReserva.PENDIENTE;
    }
}

enum EstadoReserva {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA
}
