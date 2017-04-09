package ro.ubb.c04remotingjdbctemplate.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.c04remotingjdbctemplate.common.Client;
import ro.ubb.c04remotingjdbctemplate.common.ClientService;

import java.util.List;

/**
 * Created by Petru on 4/5/2017.
 */
public class ClientServiceClient {

    @Autowired
    private ClientService clientService;

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<Client> getClients() { return clientService.getClients(); }

    public void addClient(Client c) {
        clientService.addClient(c);
    }

    public void updateClient(Client c) {
        clientService.updateClient(c);
    }

    public void deleteClient(Integer id) { clientService.deleteClient(id); }
}
