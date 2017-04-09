package ro.ubb.c04remotingjdbctemplate.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.c04remotingjdbctemplate.client.service.ClientServiceClient;
import ro.ubb.c04remotingjdbctemplate.client.service.OrderServiceClient;
import ro.ubb.c04remotingjdbctemplate.common.ClientService;
import ro.ubb.c04remotingjdbctemplate.common.OrderService;

/**
 * Created by Petru on 4/6/2017.
 */
@Configuration
public class OrderServiceClientConfig {
    @Bean
    public RmiProxyFactoryBean orderService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost:1099/OrderService");
        rmiProxy.setServiceInterface(OrderService.class);
        return rmiProxy;
    }

    @Bean
    OrderServiceClient orderServiceClient() {
        OrderServiceClient orderServiceClient = new OrderServiceClient();
        return orderServiceClient;
    }
}
