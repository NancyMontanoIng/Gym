package com.example.reto3mangym.Service;

import com.example.reto3mangym.Model.Client;
import com.example.reto3mangym.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save(Client client){
        if(client.getIdClient() == null) {
            if (client.getEmail().length() <= 45
                    && client.getPassword().length() <= 45
                    && client.getName().length() <= 250) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        } else  {
            Optional<Client> clientEncontrado = clientRepository.getClient(client.getIdClient());
            if(clientEncontrado.isPresent()){
                return clientRepository.save(client);
            } else{
                return client;
            }
        }
    }


   public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> clientEncontrado = clientRepository.getClient(client.getIdClient());
            if(!clientEncontrado.isPresent()){
                if(client.getPassword() != null){
                    clientEncontrado.get().setPassword(client.getPassword());
                }
                if(client.getName() != null){
                    clientEncontrado.get().setName((client.getName()));
                }
                if(client.getAge() != null) {
                    clientEncontrado.get().setAge((client.getAge()));
                }
                return clientRepository.save(clientEncontrado.get());
            }

        }
        return client;
    }

    public boolean delete(int Id){
        Boolean resultado = getClient(Id).map(PorEliminar -> {
            clientRepository.delete(PorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}
