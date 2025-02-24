package org.example.reservasservice.repository;

import org.example.reservasservice.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
}
