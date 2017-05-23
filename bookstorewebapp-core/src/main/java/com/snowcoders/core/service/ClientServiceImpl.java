package com.snowcoders.core.service;

import com.snowcoders.core.model.Client;
import com.snowcoders.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    protected ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(String name) {
        Client client = new Client(name);//, new HashSet<Order>(0));
        client = clientRepository.save(client);
        return client;
    }

    @Override
    public Client findOne(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    @Transactional
    public Client updateClient(Long id, String name) {
        Client client = clientRepository.findOne(id);
        client.setName(name);
        return client;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.delete(id);
    }
}
