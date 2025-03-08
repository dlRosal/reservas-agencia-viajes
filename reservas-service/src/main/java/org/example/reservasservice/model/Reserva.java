package org.example.reservasservice.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("idHotel")  // Mapea correctamente el JSON con la variable en Java
    private Integer idHotel;

    @JsonProperty("idVuelo")
    private Integer idVuelo;

    private LocalDateTime fechaReserva;
    private double total;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @PrePersist
    public void prePersist() {
        this.fechaReserva = (this.fechaReserva == null) ? LocalDateTime.now() : this.fechaReserva;
        this.estado = (this.estado == null) ? EstadoReserva.PENDIENTE : this.estado;
    }
}
