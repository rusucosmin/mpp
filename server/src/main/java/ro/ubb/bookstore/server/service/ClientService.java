package ro.ubb.bookstore.server.service;

import ro.ubb.bookstore.common.model.Client;
import ro.ubb.bookstore.common.service.IClientService;
import ro.ubb.bookstore.server.repository.Repository;

import java.util.concurrent.ExecutorService;

/**
 * ClientService
 *      - service that acts like a bridge between the ClientRepository and the User
 *      - contains the usual CRUD operations on clients and other specific methods
 */
public class ClientService extends CRUDService<Integer, Client> implements IClientService {
    public ClientService(Repository<Integer, Client> clientRepository, ExecutorService executorService) {
        super(clientRepository, executorService);
    }
}
