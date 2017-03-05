package view;

import service.BookService;
import service.ClientService;

/**
 * Created by cosmin on 3/5/17.
 */
public class Console {
    BookService bookService;
    ClientService clientService;
    public Console(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
    }

    public void run() {
        // TODO
        System.out.println(">-----Book Store-----<");
    }
}
