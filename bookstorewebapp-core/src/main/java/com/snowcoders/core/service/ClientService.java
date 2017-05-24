package com.snowcoders.core.service;

import com.snowcoders.core.model.Client;

import java.util.List;
import java.util.Set;

public interface ClientService {
    List<Client> findAll();
    Client createClient(String name);
    Client findOne(Long id);
    Client updateClient(Long id, String name, Set<Long> books);
    void deleteClient(Long id);
}
