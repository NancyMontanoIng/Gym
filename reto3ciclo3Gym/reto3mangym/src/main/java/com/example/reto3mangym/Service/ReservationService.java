package com.example.reto3mangym.Service;

import com.example.reto3mangym.Model.Reservation;
import com.example.reto3mangym.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);

        } else  {
            Optional<Reservation> reservationEncontrado = reservationRepository.getReservation(reservation.getIdReservation());
            if(reservationEncontrado.isPresent()){
                return reservationRepository.save(reservation);
            } else{
                return reservation;

            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reservationEncontrado = reservationRepository.getReservation(reservation.getIdReservation());
            if(!reservationEncontrado.isPresent()){
                if(reservation.getStartDate() != null){
                    reservationEncontrado.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate() != null){
                    reservationEncontrado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus() != null) {
                    reservationEncontrado.get().setStatus(reservation.getStatus());
                }
                return reservationRepository.save(reservationEncontrado.get());
            }

        }
        return reservation;
    }
    public boolean delete(int Id){
        Boolean resultado = getReservation(Id).map(PorEliminar -> {
            reservationRepository.delete(PorEliminar);
            return true;
        }).orElse(false);
        return resultado;
        }

}
