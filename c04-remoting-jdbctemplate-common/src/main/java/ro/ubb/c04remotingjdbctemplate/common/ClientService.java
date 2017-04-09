package ro.ubb.c04remotingjdbctemplate.common;

import java.util.List;

/**
 * Created by Petru on 4/5/2017.
 */
public interface ClientService {
    List<Client> getClients();
    void addClient(Client c);
    void deleteClient(Integer id);

    void updateClient(Client c);
}
