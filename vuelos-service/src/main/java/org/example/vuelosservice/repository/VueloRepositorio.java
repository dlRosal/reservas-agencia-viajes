package org.example.vuelosservice.repository;

import org.example.vuelosservice.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepositorio extends JpaRepository<Vuelo, Integer> {
}
