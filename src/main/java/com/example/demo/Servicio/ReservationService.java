/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Repositorio.ReservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Modelo.Reservation;
import java.util.Optional;
/**
 *
 * @author USUARIO
 */

@Service

public class ReservationService {
       @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    
    public Reservation save (Reservation reservation){
        if (reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservation1 = reservationRepository.getReservation(reservation.getIdReservation());
            if(reservation1.isEmpty()){
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if(reservation.getIdReservation() != null) {
            Optional<Reservation> reservationFound = getReservation(reservation.getIdReservation());
            if (!reservationFound.isEmpty()) {
                if (reservation.getStartDate() != null){
                    reservationFound.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    reservationFound.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null) {
                    reservationFound.get().setStatus(reservation.getStatus());
                }
                return reservationRepository.save(reservationFound.get());
            }
        }
        return reservation;
    }

    public boolean delete(int idReservation) {
        Boolean response = getReservation(idReservation).map(element -> {
            reservationRepository.delete(element);
            return true;
        }).orElse(false);
        return response;
    }
}
