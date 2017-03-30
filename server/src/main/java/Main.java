import ro.ubb.bookstore.common.model.*;
import ro.ubb.bookstore.common.model.validators.BookValidator;
import ro.ubb.bookstore.common.model.validators.ClientValidator;
import ro.ubb.bookstore.common.model.validators.OrderValidator;
import ro.ubb.bookstore.common.model.validators.Validator;
import ro.ubb.bookstore.common.service.IBookService;
import ro.ubb.bookstore.common.service.ICRUDService;
import ro.ubb.bookstore.common.service.IClientService;
import ro.ubb.bookstore.common.service.IOrderService;
import ro.ubb.bookstore.common.utils.StringSerialize;
import ro.ubb.bookstore.server.repository.*;
import ro.ubb.bookstore.server.service.BookService;
import ro.ubb.bookstore.server.service.ClientService;
import ro.ubb.bookstore.server.service.OrderService;
import ro.ubb.bookstore.server.util.TcpServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.StreamSupport;

public class Main {
    /**
     * Main class to configure and run the application
     */
    private static<ID, T extends BaseEntity<ID>> void addCreateHandler(String handlerId, ICRUDService<ID, T> service, ExecutorService executorService, TcpServer tcpServer) {
        tcpServer.addHandler(handlerId, (request) -> {
            try {
                T entity = (T)StringSerialize.fromString(request.body());
                CompletableFuture<Optional<T>> future = service.create(entity);
                Optional<T> opt = future.get();
                if(opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });
    }

    private static<T extends BaseEntity<Integer>> void addReadHandler(String handlerId, ICRUDService<Integer, T> service, ExecutorService executorService, TcpServer tcpServer) {
        tcpServer.addHandler(handlerId, (request) -> {
            try {
                Integer id = Integer.valueOf(request.body());
                CompletableFuture<Optional<T>> future = service.read(id);
                Optional<T> opt = future.get();
                if (opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });
    }

    private static<ID, T extends BaseEntity<ID>> void addReadAllHandler(String handlerId, ICRUDService<ID, T> service, ExecutorService executorService, TcpServer tcpServer) {
        tcpServer.addHandler(handlerId, (request) -> {
            try {
                CompletableFuture<Iterable<T>> future = service.readAll();
                Iterable<T> iter = future.get();
                ArrayList<T> arrayList = new ArrayList<T>();
                StreamSupport.stream(iter.spliterator(), false)
                        .forEach((e) -> arrayList.add(e));
                return new Message(Message.OK, StringSerialize.toString(arrayList));
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });
    }

    private static<T extends BaseEntity<Integer>> void addDeleteHandler(String handlerId, ICRUDService<Integer, T> service, ExecutorService executorService, TcpServer tcpServer) {
        tcpServer.addHandler(handlerId, (request) -> {
            try {
                Integer id = Integer.valueOf(request.body());
                CompletableFuture<Optional<T>> future = service.delete(id);
                Optional<T> opt = null;
                opt = future.get();
                if (opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

    }

    private static<ID, T extends BaseEntity<ID>> void addUpdateHandler(String handlerId, ICRUDService<ID, T> service, ExecutorService executorService, TcpServer tcpServer) {
        tcpServer.addHandler(handlerId, (request) -> {
            try {
                T b = (T)StringSerialize.fromString(request.body());
                CompletableFuture<Optional<T>> future = service.update(b);
                Optional<T> opt = future.get();
                if(opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

    }

    public static void main(String[] args) {
        Validator<Book>  bookValidator   = new BookValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Order> orderValidator = new OrderValidator();
        Repository<Integer, Book> bookRepository;
        Repository<Integer, Client> clientRepository;
        Repository<Integer, Order> orderRepository;

        bookRepository = new BookDatabaseRepository(
                bookValidator,
                "jdbc:postgresql://sergiu.ml:5432/mpp",
                "mpp",
                "asdf1234");
        clientRepository = new ClientDatabaseRepository(
                clientValidator,
                "jdbc:postgresql://sergiu.ml:5432/mpp",
                "mpp",
                "asdf1234");
        orderRepository = new OrderDatabaseRepository(
                orderValidator,
                "jdbc:postgresql://sergiu.ml:5432/mpp",
                "mpp",
                "asdf1234");
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

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IBookService bookService = new BookService(bookRepository, executorService);
        IClientService clientService = new ClientService(clientRepository, executorService);
        IOrderService orderService = new OrderService(orderRepository, bookService, clientService, executorService);
        TcpServer tcpServer = new TcpServer(executorService, "localhost", 1234);

        addCreateHandler(IBookService.CREATE, bookService, executorService, tcpServer);
        addCreateHandler(IClientService.CREATE, clientService, executorService, tcpServer);
        addCreateHandler(IOrderService.CREATE, orderService, executorService, tcpServer);

        addReadHandler(IBookService.READ, bookService, executorService, tcpServer);
        addReadHandler(IClientService.READ, clientService, executorService, tcpServer);
        addReadHandler(IOrderService.READ, orderService, executorService, tcpServer);

        addReadAllHandler(IBookService.READ_ALL, bookService, executorService, tcpServer);
        addReadAllHandler(IClientService.READ_ALL, clientService, executorService, tcpServer);
        addReadAllHandler(IOrderService.READ_ALL, orderService, executorService, tcpServer);


        addDeleteHandler(IBookService.DELETE, bookService, executorService, tcpServer);
        addDeleteHandler(IClientService.DELETE, clientService, executorService, tcpServer);
        addDeleteHandler(IOrderService.DELETE, orderService, executorService, tcpServer);

        addUpdateHandler(IBookService.UPDATE, bookService, executorService, tcpServer);
        addUpdateHandler(IClientService.UPDATE, clientService, executorService, tcpServer);
        addUpdateHandler(IOrderService.UPDATE, orderService, executorService, tcpServer);

        tcpServer.addHandler(IOrderService.STATISTICS, (request) -> {
            try {
                CompletableFuture<List<Map.Entry<Integer, Integer>>> future = orderService.getStatistics();
                List<Map.Entry<Integer, Integer>> list = future.get();
                ArrayList<Map.Entry<Integer, Integer>> arrayList = new ArrayList<Map.Entry<Integer, Integer>>(list);
                return new Message(Message.OK, StringSerialize.toString(arrayList));
            } catch(Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.startServer();
    }
}
