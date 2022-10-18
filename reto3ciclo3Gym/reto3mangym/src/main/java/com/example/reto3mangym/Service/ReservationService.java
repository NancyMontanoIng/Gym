package com.example.reto3mangym.Service;

import com.example.reto3mangym.Model.Reservation;
import com.example.reto3mangym.Repository.CountClient;
import com.example.reto3mangym.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    ///////////////////////////////codigo reto 5///////////////////
    public StatusService getReservationStatusReport(){
        List<Reservation>completed=reservationRepository.getReservationByStatus("completed");
        List<Reservation>cancelled=reservationRepository.getReservationByStatus("cancelled");
        return new StatusService(completed.size(),cancelled.size());
    }

    public List<Reservation> informePeriodoTiempoReservas(String dataA, String dataB){
        SimpleDateFormat parser =new SimpleDateFormat("yyyy-MM-dd");
        Date a = new Date();
        Date b = new Date();

        try {
            a = parser.parse(dataA);
            b = parser.parse(dataB);
        } catch (ParseException e){
            e.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.informePeriodoTiempoReservas(a, b);
        }else{
            return new ArrayList<>();
        }
    }

    public List<CountClient> getTopClients(){
        return reservationRepository.getTopClient();
    }
}
