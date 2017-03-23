package service;

import model.Book;
import model.Client;
import model.Order;
import model.validators.OrderException;
import repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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
        Book book = optBook.get();
        book.setCnt(book.getCnt() - order.getCnt());
        bookService.update(book);
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

        Optional<Order> oldOrderOpt = read(order.getID());
        if(!oldOrderOpt.isPresent())
            return Optional.empty();
        Order oldOrder = oldOrderOpt.get();
        Book book = bookService.read(bookId).get();

        int cntDelta = order.getCnt() - oldOrder.getCnt();
        if(book.getCnt() < cntDelta)
            throw new OrderException("Insufficient Book stock");
        book.setCnt(book.getCnt() - cntDelta);
        bookService.update(book);

        return super.update(order);
    }

    private Map<Client, Integer> getAggregatedBookCnt() {
        Map<Client, Integer> cnt = new HashMap<>();
        StreamSupport.stream(this.readAll().spliterator(), false)
            .forEach(x -> {
                Client c = clientService.read(x.getClientID()).get();
                int lastTotal = cnt.get(c);
                cnt.put(c, lastTotal + x.getCnt());
            });
        return cnt;
    }

    public List<Map.Entry<Client, Integer>> getStatistics() {
        Map<Client, Integer> cnt = getAggregatedBookCnt();
        return cnt.entrySet().stream()
                .sorted((x1, x2) -> Integer.compare(x1.getValue(), x2.getValue()))
                .collect(Collectors.toList());
    }
}
