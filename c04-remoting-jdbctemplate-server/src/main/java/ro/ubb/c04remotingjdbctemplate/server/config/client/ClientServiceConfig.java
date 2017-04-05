package ro.ubb.c04remotingjdbctemplate.server.config.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.c04remotingjdbctemplate.common.ClientService;
import ro.ubb.c04remotingjdbctemplate.server.service.ClientServiceImpl;

/**
 * Created by Petru on 4/5/2017.
 */
@Configuration
public class ClientServiceConfig {
    @Bean
    public ClientService clientService() {
        ClientService clientService = new ClientServiceImpl();
        return clientService;
    }

    @Bean
    public RmiServiceExporter rmiService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("ClientService");
        rmiService.setServiceInterface(ClientService.class);
        rmiService.setService(clientService());
        return rmiService;
    }
}
