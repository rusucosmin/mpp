package service;

import model.Client;
import repository.Repository;

public class ClientService {
    // TODO
    private Repository<Integer, Client> clientRepository;
    public ClientService(Repository<Integer, Client> clientRepository) {
        this.clientRepository = clientRepository;
    }
}
