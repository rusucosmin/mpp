package service;

import model.Client;
import repository.Repository;

/**
 * Created by cosmin on 3/5/17.
 */
public class ClientService {
    // TODO
    private Repository<Integer, Client> clientRepository;
    public ClientService(Repository<Integer, Client> clientRepository) {
        this.clientRepository = clientRepository;
    }
}
