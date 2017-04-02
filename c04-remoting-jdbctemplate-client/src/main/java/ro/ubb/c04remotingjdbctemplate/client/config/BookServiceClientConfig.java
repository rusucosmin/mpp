package ro.ubb.c04remotingjdbctemplate.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.c04remotingjdbctemplate.client.service.BookServiceClient;
import ro.ubb.c04remotingjdbctemplate.common.BookService;

/**
 * @author radu.
 */

@Configuration
public class BookServiceClientConfig {

    @Bean
    public RmiProxyFactoryBean bookService() {
        RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
        rmiProxy.setServiceUrl("rmi://localhost:1099/BookService");
        rmiProxy.setServiceInterface(BookService.class);
        return rmiProxy;
    }

    @Bean
    public BookServiceClient bookServiceClient() {
        BookServiceClient bookServiceClient = new BookServiceClient();
        return bookServiceClient;
    }


}
