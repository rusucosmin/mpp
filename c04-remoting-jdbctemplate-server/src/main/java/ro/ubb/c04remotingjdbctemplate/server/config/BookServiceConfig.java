package ro.ubb.c04remotingjdbctemplate.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.c04remotingjdbctemplate.common.BookService;
import ro.ubb.c04remotingjdbctemplate.server.service.BookServiceImpl;


/**
 * @author radu.
 */

@Configuration
public class BookServiceConfig {

    @Bean
    public BookService bookService() {
        BookService bookService = new BookServiceImpl();
        return bookService;
    }

    @Bean
    public RmiServiceExporter rmiService() {
        RmiServiceExporter rmiService = new RmiServiceExporter();
        rmiService.setServiceName("BookService");
        rmiService.setServiceInterface(BookService.class);
        rmiService.setService(bookService());
        return rmiService;
    }
}
