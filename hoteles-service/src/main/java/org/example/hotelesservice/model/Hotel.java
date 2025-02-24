package org.example.hotelesservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hoteles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String ubicacion;
    private int estrellas;
    private double precioPorNoche;
    private boolean disponibilidad;
}
