import ro.ubb.bookstore.common.model.Book;
import ro.ubb.bookstore.common.model.Message;
import ro.ubb.bookstore.common.model.validators.*;
import ro.ubb.bookstore.common.service.IBookService;
import ro.ubb.bookstore.server.repository.*;
import ro.ubb.bookstore.server.service.BookService;
import ro.ubb.bookstore.common.utils.StringSerialize;
import ro.ubb.bookstore.server.util.TcpServer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.StreamSupport;
//import ro.ubb.bookstore..view.Console;

public class Main {
    /**
     * Main class to configure and run the application
     * @param args
     */
    public static void main(String[] args) {
        Validator<Book>  bookValidator   = new BookValidator();
//        Validator<Client> clientValidator = new ClientValidator();
//        Validator<Order> orderValidator = new OrderValidator();
        Repository<Integer, Book> bookRepository;
//        Repository<Integer, Client> clientRepository;
//        Repository<Integer, Order> orderRepository;
        /*
        bookRepository = new BookDatabaseRepository(
                bookValidator,
                "jdbc:postgresql://sergiu.ml:5432/mpp",
                "cosmin",
                "asdf1234");
        R
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
        bookRepository = new InMemoryRepository<>(bookValidator);
//        clientRepository = new InMemoryRepository<>(clientValidator);
//        orderRepository = new InMemoryRepository<>(orderValidator);

        //file repo
        /*
        bookRepository = new FileRepository<>(bookValidator, "./data/books.file");
        clientRepository = new FileRepository<>(clientValidator, "./data/clients.file");
        orderRepository = new FileRepository<>(orderValidator, "./data/orders.file");

        */

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IBookService bookService = new BookService(bookRepository, executorService);
        TcpServer tcpServer = new TcpServer(executorService, "localhost", 1234);

        tcpServer.addHandler(IBookService.CREATE, (request) -> {
            try {
                Book b = (Book)StringSerialize.fromString(request.body());
                CompletableFuture<Optional<Book>> future = bookService.create(b);
                Optional<Book> opt = future.get();
                if(opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IBookService.READ, (request) -> {
            try {
                int id = Integer.valueOf(request.body());
                CompletableFuture<Optional<Book>> future = bookService.read(id);
                Optional<Book> opt = null;
                opt = future.get();
                if (opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IBookService.READ_ALL, (request) -> {
            try {
                CompletableFuture<Iterable<Book>> future = bookService.readAll();
                Iterable<Book> iter = future.get();
                ArrayList<Book> arrayList = new ArrayList<Book>();
                StreamSupport.stream(iter.spliterator(), false)
                        .forEach((e) -> arrayList.add(e));
                return new Message(Message.OK, StringSerialize.toString(arrayList));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IBookService.DELETE, (request) -> {
            try {
                int id = Integer.valueOf(request.body());
                CompletableFuture<Optional<Book>> future = bookService.delete(id);
                Optional<Book> opt = null;
                opt = future.get();
                if (opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IBookService.UPDATE, (request) -> {
            try {
                Book b = (Book)StringSerialize.fromString(request.body());
                CompletableFuture<Optional<Book>> future = bookService.update(b);
                Optional<Book> opt = future.get();
                if(opt.isPresent())
                    return new Message(Message.OK, StringSerialize.toString(opt.get()));
                else
                    return new Message(Message.OK, "null");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.startServer();

        //ClientService clientService = new ClientService(clientRepository);
        //OrderService orderService = new OrderService(orderRepository, bookService, clientService);



    }
}
