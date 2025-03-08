package org.example.reservasservice.service;

import org.example.reservasservice.model.Reserva;
import org.example.reservasservice.repository.ReservaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
public class ReservaServicio {

    private final ReservaRepositorio reservaRepositorio;
    private final RestTemplate restTemplate;

    private static final String HOTEL_SERVICE_URL = "http://localhost:8081/hoteles/";
    private static final String VUELO_SERVICE_URL = "http://localhost:8082/vuelos/";

    public ReservaServicio(ReservaRepositorio reservaRepositorio, RestTemplate restTemplate) {
        this.reservaRepositorio = reservaRepositorio;
        this.restTemplate = restTemplate;
    }

    public List<Reserva> listarReservas() {
        return reservaRepositorio.findAll();
    }

    public Reserva obtenerReservaPorId(Integer id) {
        return reservaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    public Reserva guardarReserva(Reserva reserva) {
        if (reserva.getIdHotel() == null || reserva.getIdVuelo() == null) {
            throw new RuntimeException("❌ ID del hotel o vuelo es nulo. No se puede procesar la reserva.");
        }

        System.out.println("🛎️ ID del hotel recibido: " + reserva.getIdHotel());
        System.out.println("✈️ ID del vuelo recibido: " + reserva.getIdVuelo());

        ResponseEntity<Map> hotelResponse = restTemplate.getForEntity(HOTEL_SERVICE_URL + reserva.getIdHotel(), Map.class);
        Integer hotelId = hotelResponse.getStatusCode().is2xxSuccessful() ? (Integer) hotelResponse.getBody().get("id") : null;

        ResponseEntity<Map> vueloResponse = restTemplate.getForEntity(VUELO_SERVICE_URL + reserva.getIdVuelo(), Map.class);
        Integer vueloId = vueloResponse.getStatusCode().is2xxSuccessful() ? (Integer) vueloResponse.getBody().get("id") : null;

        System.out.println("🏨 Hotel encontrado: " + hotelId);
        System.out.println("🛫 Vuelo encontrado: " + vueloId);

        if (hotelId != null && vueloId != null) {
            return reservaRepositorio.save(reserva);
        } else {
            throw new RuntimeException("❌ El hotel o el vuelo no existen.");
        }
    }
}
