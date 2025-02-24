package org.example.reservasservice.service;

import org.example.reservasservice.model.Reserva;
import org.example.reservasservice.repository.ReservaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ReservaServicio {

    private final ReservaRepositorio reservaRepositorio;
    private final WebClient.Builder webClientBuilder;

    public ReservaServicio(ReservaRepositorio reservaRepositorio, WebClient.Builder webClientBuilder) {
        this.reservaRepositorio = reservaRepositorio;
        this.webClientBuilder = webClientBuilder;
    }

    public List<Reserva> listarReservas() {
        return reservaRepositorio.findAll();
    }

    public Reserva obtenerReservaPorId(Integer id) {
        return reservaRepositorio.findById(id).orElse(null);
    }

    public Reserva guardarReserva(Reserva reserva) {
        // Verificar si el hotel existe
        Boolean hotelExiste = webClientBuilder.build()
                .get()
                .uri("http://hoteles-service/hoteles/" + reserva.getIdHotel())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        // Verificar si el vuelo existe
        Boolean vueloExiste = webClientBuilder.build()
                .get()
                .uri("http://vuelos-service/vuelos/" + reserva.getIdVuelo())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (hotelExiste != null && vueloExiste != null && hotelExiste && vueloExiste) {
            return reservaRepositorio.save(reserva);
        } else {
            throw new RuntimeException("El hotel o el vuelo no existen.");
        }
    }

    public void eliminarReserva(Integer id) {
        reservaRepositorio.deleteById(id);
    }
}
