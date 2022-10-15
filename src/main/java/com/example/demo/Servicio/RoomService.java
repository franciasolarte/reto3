/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Repositorio.RoomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Modelo.Room;
/**
 *
 * @author USUARIO
 */

@Service

public class RoomService {
      @Autowired
    private RoomRepository roomRepository;
    
    public List<Room> getAll(){
        return roomRepository.getAll();
    }
    
    public Optional<Room> getRoom(int id){
        return roomRepository.getRoom(id);
    }
    
    public Room save (Room room){
        if (room.getId() == null){
            return roomRepository.save(room);
        } else {
            Optional<Room> room1 = roomRepository.getRoom(room.getId());
            if(room1.isEmpty()){
                return roomRepository.save(room);
            } else {
                return room;
            }
        }
    }

    public Room update(Room room) {
        if(room.getId() != null) {
            Optional<Room> roomFound = getRoom(room.getId());
            if (!roomFound.isEmpty()) {
                if (room.getName() != null){
                    roomFound.get().setName(room.getName());
                }
                if (room.getHotel() != null) {
                    roomFound.get().setHotel(room.getHotel());
                }
                if(room.getStars()!=null) {
                    roomFound.get().setStars(room.getStars());
                }
                if(room.getDescription()!=null) {
                    roomFound.get().setDescription(room.getDescription());
                }
                if (room.getCategory() != null) {
                    roomFound.get().setCategory(room.getCategory());
                }
                return roomRepository.save(roomFound.get());
            }
        }
        return room;
    }

    public boolean delete(int id) {
        Boolean response = getRoom(id).map(element -> {
            roomRepository.delete(element);
            return true;
        }).orElse(false);
        return response;
    }
}
