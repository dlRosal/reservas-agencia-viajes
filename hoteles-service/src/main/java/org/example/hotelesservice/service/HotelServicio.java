package org.example.hotelesservice.service;

import org.example.hotelesservice.model.Hotel;
import org.example.hotelesservice.repository.HotelRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServicio {

    private final HotelRepositorio hotelRepositorio;

    public HotelServicio(HotelRepositorio hotelRepositorio) {
        this.hotelRepositorio = hotelRepositorio;
    }

    public List<Hotel> listarHoteles() {
        return hotelRepositorio.findAll();
    }

    public Hotel obtenerHotelPorId(Integer id) {
        return hotelRepositorio.findById(id).orElse(null);
    }

    public Hotel guardarHotel(Hotel hotel) {
        return hotelRepositorio.save(hotel);
    }

    public void eliminarHotel(Integer id) {
        hotelRepositorio.deleteById(id);
    }
}
