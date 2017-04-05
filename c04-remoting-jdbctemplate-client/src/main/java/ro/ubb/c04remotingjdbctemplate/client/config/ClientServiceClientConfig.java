package ro.ubb.c04remotingjdbctemplate.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.c04remotingjdbctemplate.client.service.ClientServiceClient;
import ro.ubb.c04remotingjdbctemplate.common.BookService;
import ro.ubb.c04remotingjdbctemplate.common.ClientService;

/**
 * Created by Petru on 4/5/2017.
 */
@Configuration
public class ClientServiceClientConfig {

    @Bean
    public RmiProxyFactoryBean clientService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost:1099/ClientService");
        rmiProxy.setServiceInterface(ClientService.class);
        return rmiProxy;
    }

    @Bean
    ClientServiceClient clientServiceClient() {
        ClientServiceClient clientServiceClient = new ClientServiceClient();
        return clientServiceClient;
    }
}
