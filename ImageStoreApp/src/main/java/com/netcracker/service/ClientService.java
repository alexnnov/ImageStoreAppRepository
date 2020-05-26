package com.netcracker.service;

import com.netcracker.entity.Client;

import com.netcracker.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void add(Client client){
        clientRepository.save(client);
    }

    public void update(Client client){
        clientRepository.save(client);
    }

    public void remove(int id) {
        clientRepository.deleteById(id);
    }

    public Client find(int id){
        Optional<Client> optional = clientRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }



}
