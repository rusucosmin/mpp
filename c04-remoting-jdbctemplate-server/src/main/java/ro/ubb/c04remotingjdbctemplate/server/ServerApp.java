package ro.ubb.c04remotingjdbctemplate.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author radu.
 */
public class ServerApp {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("ro.ubb.c04remotingjdbctemplate.server.config.book");
        new AnnotationConfigApplicationContext("ro.ubb.c04remotingjdbctemplate.server.config.client");
        new AnnotationConfigApplicationContext("ro.ubb.c04remotingjdbctemplate.server.config.order");
    }
}
