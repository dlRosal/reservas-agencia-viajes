package org.example.hotelesservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hoteles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Integer disponibilidad;
}
