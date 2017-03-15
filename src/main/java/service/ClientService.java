package service;

import model.Client;
import repository.Repository;

/**
 * ClientService
 *      - service that acts like a bridge between the ClientRepository and the User
 *      - contains the usual CRUD operations on clients and other specific methods
 */
public class ClientService extends CRUDService<Integer, Client> {
    public ClientService(Repository<Integer, Client> clientRepository) {
        super(clientRepository);
    }
}
