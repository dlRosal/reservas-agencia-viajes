package org.example.reservasservice.service;

import org.example.reservasservice.model.Reserva;
import org.example.reservasservice.repository.ReservaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Servicio que maneja la lógica de negocio relacionada con las reservas.
 */
@Service
public class ReservaServicio {

    private final ReservaRepositorio reservaRepositorio;
    private final RestTemplate restTemplate;

    // URLs de los servicios externos
    private static final String HOTEL_SERVICE_URL = "http://localhost:8081/hoteles/";
    private static final String VUELO_SERVICE_URL = "http://localhost:8082/vuelos/";

    /**
     * Constructor que inyecta las dependencias necesarias.
     *
     * @param reservaRepositorio Repositorio para gestionar reservas en la base de datos.
     * @param restTemplate Cliente para realizar peticiones HTTP a otros microservicios.
     */
    public ReservaServicio(ReservaRepositorio reservaRepositorio, RestTemplate restTemplate) {
        this.reservaRepositorio = reservaRepositorio;
        this.restTemplate = restTemplate;
    }

    /**
     * Lista todas las reservas almacenadas en la base de datos.
     *
     * @return Lista de reservas.
     */
    public List<Reserva> listarReservas() {
        return reservaRepositorio.findAll();
    }

    /**
     * Obtiene una reserva por su ID.
     *
     * @param id Identificador de la reserva.
     * @return Objeto Reserva si se encuentra en la base de datos.
     * @throws RuntimeException Si la reserva no existe.
     */
    public Reserva obtenerReservaPorId(Integer id) {
        return reservaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    /**
     * Guarda una nueva reserva en la base de datos, validando la existencia del hotel y el vuelo antes de guardarla.
     *
     * @param reserva Objeto Reserva con los datos de la reserva.
     * @return La reserva guardada.
     * @throws RuntimeException Si el ID del hotel o vuelo es nulo, o si el hotel o vuelo no existen.
     */
    public Reserva guardarReserva(Reserva reserva) {
        if (reserva.getIdHotel() == null || reserva.getIdVuelo() == null) {
            throw new RuntimeException("ID del hotel o vuelo es nulo. No se puede procesar la reserva.");
        }

        // Consultar la existencia del hotel
        ResponseEntity<Map> hotelResponse = restTemplate.getForEntity(HOTEL_SERVICE_URL + reserva.getIdHotel(), Map.class);
        Integer hotelId = hotelResponse.getStatusCode().is2xxSuccessful() ? (Integer) hotelResponse.getBody().get("id") : null;

        // Consultar la existencia del vuelo
        ResponseEntity<Map> vueloResponse = restTemplate.getForEntity(VUELO_SERVICE_URL + reserva.getIdVuelo(), Map.class);
        Integer vueloId = vueloResponse.getStatusCode().is2xxSuccessful() ? (Integer) vueloResponse.getBody().get("id") : null;

        // Verificar que ambos servicios devolvieron resultados válidos antes de guardar la reserva
        if (hotelId != null && vueloId != null) {
            return reservaRepositorio.save(reserva);
        } else {
            throw new RuntimeException("El hotel o el vuelo no existen.");
        }
    }
}
