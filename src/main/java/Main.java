import model.Book;
import model.Client;
import model.Order;
import model.validators.BookValidator;
import model.validators.ClientValidator;
import model.validators.Validator;
import model.validators.ValidatorException;
import repository.InMemoryRepository;
import repository.Repository;
import repository.XmlRepository;
import service.BookService;
import service.ClientService;
import service.OrderService;
import view.Console;

import javax.swing.text.html.parser.Entity;

public class Main {
    /**
     * Main class to configure and run the application
     * @param args
     */
    public static void main(String[] args) {
        // in memory repo
        Validator<Book> bookValidator = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Repository<Integer, Book> bookRepository = new XmlRepository<>(bookValidator, "./data/books.xml");
        Repository<Integer, Client> clientRepository = new XmlRepository<>(clientValidator, "./data/clients.xml");
        BookService bookService = new BookService(bookRepository);
        ClientService clientService = new ClientService(clientRepository);
        Console console = new Console(bookService, clientService);
        console.run();
    }
}
