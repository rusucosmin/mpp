package ro.ubb.bookstore.common.service;

import ro.ubb.bookstore.common.model.Client;

public interface IClientService extends ICRUDService<Integer, Client> {
    String CREATE = "createClient";
    String READ = "readClient";
    String READ_ALL = "readAllClients";
    String UPDATE = "updateClient";
    String DELETE = "deleteClient";
}
