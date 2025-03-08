package org.example.reservasservice.repository;

import org.example.reservasservice.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
}
