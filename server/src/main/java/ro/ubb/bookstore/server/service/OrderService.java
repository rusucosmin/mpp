package ro.ubb.bookstore.server.service;

import ro.ubb.bookstore.common.model.Book;
import ro.ubb.bookstore.common.model.Client;
import ro.ubb.bookstore.common.model.Order;
import ro.ubb.bookstore.common.model.validators.OrderException;
import ro.ubb.bookstore.common.service.IBookService;
import ro.ubb.bookstore.common.service.IClientService;
import ro.ubb.bookstore.common.service.IOrderService;
import ro.ubb.bookstore.server.repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class OrderService extends CRUDService<Integer, Order> implements IOrderService {
    private IBookService bookService;
    private IClientService clientService;

    public OrderService(Repository<Integer, Order> entityRepository, IBookService bookService, IClientService clientService, ExecutorService executorService) {
        super(entityRepository, executorService);
        this.bookService = bookService;
        this.clientService = clientService;
    }

    @Override
    public CompletableFuture<Optional<Order>> create(Order order) throws OrderException {
        int bookId = order.getBookID();
        int clientId = order.getClientID();
        CompletableFuture<Optional<Book>> optBook = bookService.read(bookId);
        CompletableFuture<Optional<Client>> optClient = clientService.read(clientId);
        CompletableFuture<Optional<Order>> future = optClient.thenAcceptBoth(optBook, (clientOpt, bookOpt) -> {
            if (!clientOpt.isPresent())
                throw new OrderException("Inexistent Client");
            if (!bookOpt.isPresent())
                throw new OrderException("Inexistent Book");
            Book book = bookOpt.get();
            book.setCnt(book.getCnt() - order.getCnt());
            bookService.update(book);
        }).thenCompose(s -> super.create(order));
        return future;
    }

    @Override
    public CompletableFuture<Optional<Order>> update(Order order) throws OrderException {
        int bookId = order.getBookID();
        int clientId = order.getClientID();
        CompletableFuture<Optional<Client>> clientFuture = clientService.read(clientId);
        CompletableFuture<Optional<Book>> bookFuture = bookService.read(bookId);
        CompletableFuture<Optional<Order>> oldOrderFuture = read(order.getID());

        CompletableFuture<Optional<Order>> future = clientFuture.thenCombine(bookFuture, (clientOpt, bookOpt) -> {
            if (!clientOpt.isPresent())
                throw new OrderException("Inexistent Client");
            if (!bookOpt.isPresent())
                throw new OrderException("Inexistent Book");
            Book book = bookOpt.get();
            return book;
        }).thenCombine(oldOrderFuture, (book, oldOrderOpt) -> {
            if(!oldOrderOpt.isPresent())
                return Optional.empty();
            Order oldOrder = oldOrderOpt.get();
            int cntDelta = order.getCnt() - oldOrder.getCnt();
            if(book.getCnt() < cntDelta)
                throw new OrderException("Insufficient Book stock");
            book.setCnt(book.getCnt() - cntDelta);
            bookService.update(book);
            return book;
        }).thenCompose(s -> super.update(order));
        return future;
    }

    private CompletableFuture<Map<Integer, Integer>> getAggregatedBookCnt() {
        CompletableFuture<Iterable<Order>> future = this.readAll();
        return future.thenApply(iter ->
            StreamSupport.stream(iter.spliterator(), false)
                .collect(Collectors.groupingBy(Order::getClientID, Collectors.summingInt(Order::getCnt)))
        );
    }

    public CompletableFuture<List<Map.Entry<Integer, Integer>>> getStatistics() {
        CompletableFuture<Map<Integer, Integer>> future = getAggregatedBookCnt();
        return future.thenApply(cnt ->
                cnt.entrySet().stream()
                        .sorted((x1, x2) -> Integer.compare(x1.getValue(), x2.getValue()))
                        .collect(Collectors.toList()));
    }
}
