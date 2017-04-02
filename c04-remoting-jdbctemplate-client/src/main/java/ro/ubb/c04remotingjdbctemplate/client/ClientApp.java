package ro.ubb.c04remotingjdbctemplate.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.c04remotingjdbctemplate.client.service.BookServiceClient;
import ro.ubb.c04remotingjdbctemplate.common.Book;


import java.util.List;

/**
 * @author radu.
 */
public class ClientApp {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.c04remotingjdbctemplate.client.config");
        BookServiceClient bookServiceClient =context.getBean(BookServiceClient.class);

        //TODO implement ClientUI
        List<Book> books = bookServiceClient.getBooks();
        books.stream().forEach(System.out::println);


    }
}
