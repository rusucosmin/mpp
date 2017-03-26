import model.Book;
import model.Client;
import model.Order;
import model.validators.*;
import repository.*;
import service.BookService;
import service.ClientService;
import service.OrderService;
import view.Console;

public class Main {
    /**
     * Main class to configure and run the application
     * @param args
     */
    public static void main(String[] args) {
        Validator<Book>   bookValidator   = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Order> orderValidator = new OrderValidator();
        Repository<Integer, Book> bookRepository = new BookDatabaseRepository(
                bookValidator,
                "jdbc:postgresql://localhost:5432/mpp",
                "sergiu",
                "asdf1234");
        Repository<Integer, Client> clientRepository = new ClientDatabaseRepository(
                clientValidator,
                "jdbc:postgresql://localhost:5432/mpp",
                "sergiu",
                "asdf1234");
        Repository<Integer, Order> orderRepository = new OrderDatabaseRepository(
                orderValidator,
                "jdbc:postgresql://localhost:5432/mpp",
                "sergiu",
                "asdf1234");

        bookRepository = new XmlRepository<>(bookValidator, "./data/books.xml");
        clientRepository = new XmlRepository<>(clientValidator, "./data/clients.xml");
        orderRepository = new XmlRepository<>(orderValidator, "./data/orders.xml");

//        bookRepository = new InMemoryRepository<>(bookValidator);
//        clientRepository = new InMemoryRepository<>(clientValidator);
//        orderRepository = new InMemoryRepository<>(orderValidator);

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
