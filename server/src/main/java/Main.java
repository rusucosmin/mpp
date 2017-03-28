import ro.ubb.bookstore.server.model.Book;
import ro.ubb.bookstore.server.model.Client;
import ro.ubb.bookstore.server.model.Order;
import ro.ubb.bookstore.server.model.validators.*;
import ro.ubb.bookstore.server.repository.*;
import ro.ubb.bookstore.server.service.BookService;
import ro.ubb.bookstore.server.service.ClientService;
import ro.ubb.bookstore.server.service.OrderService;
import ro.ubb.bookstore.server.view.Console;

public class Main {
    /**
     * Main class to configure and run the application
     * @param args
     */
    public static void main(String[] args) {
        Validator<Book>  bookValidator   = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Order> orderValidator = new OrderValidator();
        Repository<Integer, Book> bookRepository;
        Repository<Integer, Client> clientRepository;
        Repository<Integer, Order> orderRepository;
        /*
        bookRepository = new BookDatabaseRepository(
                bookValidator,
                "jdbc:postgresql://sergiu.ml:5432/mpp",
                "cosmin",
                "asdf1234");
        Repository<Integer, Client> clientRepository = new ClientDatabaseRepository(
                clientValidator,
                "jdbc:postgresql://sergiu.ml:5432/mpp",
                "cosmin",
                "asdf1234");
        Repository<Integer, Order> orderRepository = new OrderDatabaseRepository(
                orderValidator,
                "jdbc:postgresql://sergiu.ml:5432/mpp",
                "cosmin",
                "asdf1234");
        */
        ///xml repo
//        bookRepository = new XmlRepository<>(bookValidator, "./data/books.xml");
//        clientRepository = new XmlRepository<>(clientValidator, "./data/clients.xml");
//        orderRepository = new XmlRepository<>(orderValidator, "./data/orders.xml");

        ///in memory rep
//        bookRepository = new InMemoryRepository<>(bookValidator);
//        clientRepository = new InMemoryRepository<>(clientValidator);
//        orderRepository = new InMemoryRepository<>(orderValidator);

        //file repo
        bookRepository = new FileRepository<>(bookValidator, "./data/books.file");
        clientRepository = new FileRepository<>(clientValidator, "./data/clients.file");
        orderRepository = new FileRepository<>(orderValidator, "./data/orders.file");

        BookService bookService = new BookService(bookRepository);
        ClientService clientService = new ClientService(clientRepository);
        OrderService orderService = new OrderService(orderRepository, bookService, clientService);

        Console console = new Console(bookService, clientService, orderService);
        console.run();
    }
}
