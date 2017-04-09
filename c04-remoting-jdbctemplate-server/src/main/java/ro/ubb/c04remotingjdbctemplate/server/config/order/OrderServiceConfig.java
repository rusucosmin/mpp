package ro.ubb.c04remotingjdbctemplate.server.config.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.c04remotingjdbctemplate.common.ClientService;
import ro.ubb.c04remotingjdbctemplate.common.OrderService;
import ro.ubb.c04remotingjdbctemplate.server.service.OrderServiceImpl;

/**
 * Created by Petru on 4/5/2017.
 */
@Configuration
public class OrderServiceConfig {
    @Bean
    public OrderService orderService() {
        OrderService orderService = new OrderServiceImpl();
        return orderService;
    }

    @Bean
    public RmiServiceExporter rmiService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("OrderService");
        rmiService.setServiceInterface(OrderService.class);
        rmiService.setService(orderService());
        return rmiService;
    }
}
