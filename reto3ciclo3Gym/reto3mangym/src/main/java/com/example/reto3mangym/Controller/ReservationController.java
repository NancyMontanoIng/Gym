package com.example.reto3mangym.Controller;

import com.example.reto3mangym.Model.Client;

import com.example.reto3mangym.Model.Machine;
import com.example.reto3mangym.Model.Reservation;
import com.example.reto3mangym.Repository.CountClient;
import com.example.reto3mangym.Service.ReservationService;
import com.example.reto3mangym.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    //ruta de peticion GET   /api/Reservation/all
    @GetMapping("/all")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationService.getReservation(id);
    }

    //ruta de peticion POST    /api/Reservation/save
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);

    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return reservationService.delete(id);
    }


    ////////////////////CODIGO PARA RETO5///////////////
    @GetMapping("/report-clients")
    public List<CountClient> getReservationsReportClient() {
        return reservationService.getTopClients();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservationsReportDates(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo) {
        return reservationService.informePeriodoTiempoReservas(dateOne, dateTwo);
    }

    @GetMapping("/report-status")
    public StatusService getReservationsStatusReport() {
        return reservationService.getReservationStatusReport();
    }
}



