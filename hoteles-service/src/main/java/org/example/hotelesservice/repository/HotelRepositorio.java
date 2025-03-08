package org.example.hotelesservice.repository;

import org.example.hotelesservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepositorio extends JpaRepository<Hotel, Integer> {
}
