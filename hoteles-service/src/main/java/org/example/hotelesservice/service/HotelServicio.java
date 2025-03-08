package org.example.hotelesservice.service;

import org.example.hotelesservice.model.Hotel;
import org.example.hotelesservice.repository.HotelRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServicio {

    private final HotelRepositorio hotelRepositorio;

    public HotelServicio(HotelRepositorio hotelRepositorio) {
        this.hotelRepositorio = hotelRepositorio;
    }

    public List<Hotel> listarHoteles() {
        return hotelRepositorio.findAll();
    }

    // 🔥 Nuevo método para obtener hotel por ID
    public Optional<Hotel> obtenerHotelPorId(Integer id) {
        return hotelRepositorio.findById(id);
    }
}
