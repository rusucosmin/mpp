package service;

import model.Book;
import model.Client;
import model.Order;
import model.validators.BookStoreException;
import model.validators.OrderException;
import model.validators.ValidatorException;
import repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    @Override
    public Optional<Order> create(Order order) throws OrderException {
        int bookId = order.getBookID();
        int clientId = order.getClientID();
        if (!clientService.read(clientId).isPresent())
            throw new OrderException("Inexistent Client");
        Optional<Book> optBook = bookService.read(bookId);
        if (!optBook.isPresent())
            throw new OrderException("Inexistent Book");
        if(optBook.get().getCnt() < order.getCnt())
            throw new OrderException("Insufficient Book stock");
        return super.create(order);
    }

    @Override
    public Optional<Order> update(Order order) throws OrderException {
        int bookId = order.getBookID();
        int clientId = order.getClientID();
        if (!clientService.read(clientId).isPresent())
            throw new OrderException("Inexistent Client");
        Optional<Book> optBook = bookService.read(bookId);
        if (!optBook.isPresent())
            throw new OrderException("Inexistent Book");
        if(optBook.get().getCnt() < order.getCnt())
            throw new OrderException("Insufficient Book stock");
        return super.update(order);
    }

    // TODO fixme
    public Map<Client, Integer> getAggregatedBookCnt() {
        Map<Client, Integer> cnt = new HashMap<>();
        return cnt;
    }
}
