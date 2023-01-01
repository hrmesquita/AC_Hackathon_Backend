package org.greytcoders.rent_a_bs.service;

import org.greytcoders.rent_a_bs.models.Reservation;
import org.greytcoders.rent_a_bs.models.User;
import org.greytcoders.rent_a_bs.repositories.ReservesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservesRepo reservesRepo;

    public Reservation insert(Reservation reservation) {
        Reservation newReservation = reservesRepo.save(reservation);
        return newReservation;
    }

    public Optional<Iterable<Reservation>> getAll() {
        return Optional.of(reservesRepo.findAll());
    }


    public Optional<Reservation> getUserById(Long id) {
        return reservesRepo.findById(id);
    }

    public void deleteId(Long id){
        reservesRepo.deleteById(id);
    }

}
