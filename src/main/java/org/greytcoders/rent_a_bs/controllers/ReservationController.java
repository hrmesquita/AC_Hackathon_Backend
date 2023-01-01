package org.greytcoders.rent_a_bs.controllers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.greytcoders.rent_a_bs.models.Reservation;
import org.greytcoders.rent_a_bs.models.User;
import org.greytcoders.rent_a_bs.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    private static final Logger LOGGER = LogManager.getLogger(ReservationController.class);

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {

        for (Reservation reserv : reservationService.getAll().get()) {
            if (reserv.getDate().equals(reservation.getDate()) &&
                    reserv.getProduct_id().equals(reservation.getProduct_id())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        try {

            LOGGER.info("operation='createReservation', create reservation='{}'", reservation);

            Reservation newReservation = reservationService.insert(reservation);
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
        } catch (Error error) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Reservation> getReservationId(@PathVariable Long id) {

        LOGGER.info("operation='getUserById', id='{}'", id);

        Optional<Reservation> opReservation = reservationService.getUserById(id);
        if (opReservation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(opReservation.get(), HttpStatus.OK);
        }
    }

    @GetMapping()
    public ResponseEntity<Iterable<Reservation>> getAllUser() {

        LOGGER.info("operation='getAllUser'");

        Optional<Iterable<Reservation>> allReservations = reservationService.getAll();
        if (allReservations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allReservations.get(), HttpStatus.OK);
        }
    }

}
