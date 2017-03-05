import hello.Greeter;
import model.Book;
import model.Client;
import model.validators.BookValidator;
import model.validators.ClientValidator;
import model.validators.Validator;
import org.joda.time.LocalTime;
import repository.InMemoryRepository;
import repository.Repository;
import service.BookService;
import service.ClientService;
import view.Console;

public class Main {
    public static void main(String[] args) {
        // in memory repo
        Validator<Book> bookValidator = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Repository<Integer, Book> bookRepository = new InMemoryRepository<Integer, Book>(bookValidator);
        Repository<Integer, Client> clientRepository = new InMemoryRepository<Integer, Client>(clientValidator);
        BookService bookService = new BookService(bookRepository);
        ClientService clientService = new ClientService(clientRepository);
        Console console = new Console(bookService, clientService);
        console.run();
    }
}
