package service;

import model.Client;
import repository.Repository;

public class ClientService extends CRUDService<Integer, Client> {
    // TODO
    public ClientService(Repository<Integer, Client> clientRepository) {
        super(clientRepository);
    }
}
