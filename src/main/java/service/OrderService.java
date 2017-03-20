package service;

import model.Client;
import model.Order;
import model.validators.BookStoreException;
import repository.Repository;


public class OrderService extends CRUDService<Integer, Order> {
    private BookService bookService;
    private ClientService clientService;

    public OrderService(Repository<Integer, Order> entityRepository, BookService bookService, ClientService clientService) {
        super(entityRepository);
        this.bookService = bookService;
        this.clientService = clientService;
    }
    // TODO: rewrite every method from CRUD service (with @Override) and before
    // calling the methods, check if books are present (and enough)
    // and if clients also exists
}
