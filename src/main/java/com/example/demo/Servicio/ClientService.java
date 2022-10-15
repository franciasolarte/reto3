/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Repositorio.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Modelo.Client;
/**
 *
 * @author USUARIO
 */

@Service

public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }
    
     public Client save (Client client){
        if (client.getIdClient() == null){
            return clientRepository.save(client);
        } else {
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if(client1.isEmpty()){
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client) {
        if(client.getIdClient() != null) {
            Optional<Client> clientFound = getClient(client.getIdClient());
            if (!clientFound.isEmpty()) {
                if (client.getName() != null){
                    clientFound.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    clientFound.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null) {
                    clientFound.get().setPassword(client.getPassword());
                }
                return clientRepository.save(clientFound.get());
            }
        }
        return client;
    }

    public boolean delete(int idClient) {
        Boolean response = getClient(idClient).map(element -> {
            clientRepository.delete(element);
            return true;
        }).orElse(false);
        return response;

    }




}
