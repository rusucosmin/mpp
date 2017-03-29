package ro.ubb.bookstore.client.service;

import ro.ubb.bookstore.client.tcp.TcpClient;
import ro.ubb.bookstore.common.model.Book;
import ro.ubb.bookstore.common.service.IBookService;

import java.util.concurrent.ExecutorService;

public class BookServiceClient extends CRUDServiceClient<Integer, Book> implements IBookService {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public BookServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        super(executorService, tcpClient, "Book");
    }

    /*
    @Override
    public CompletableFuture<Optional<Book>> create(Book entity) throws ValidatorException {
        CompletableFuture<Optional<Book>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Message request = new Message(IBookService.CREATE, StringSerialize.toString(entity));
                Message response = tcpClient.sendAndReceive(request);
                if(response.header().equals(Message.OK))
                    if(response.body().equals("null"))
                        return Optional.empty();
                    else
                        return Optional.ofNullable((Book)StringSerialize.fromString(response.body()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Could not save book to database");
        }, executorService);
        return future;
    }

    @Override
    public CompletableFuture<Optional<Book>> read(Integer integer) {
        CompletableFuture<Optional<Book>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Message request = new Message(IBookService.READ, integer.toString());
                Message response = tcpClient.sendAndReceive(request);
                if(response.header() == Message.OK)
                    if(response.body() == "null")
                        return Optional.empty();
                    else
                        return Optional.ofNullable((Book)StringSerialize.fromString(response.body()));
            } catch(Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Could not read book from database");
        }, executorService);
        return future;
    }

    @Override
    public CompletableFuture<Iterable<Book>> readAll() {
        CompletableFuture<Iterable<Book>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Message request = new Message(IBookService.READ_ALL, "");
                Message response = tcpClient.sendAndReceive(request);
                if(response.header().equals(Message.OK)) {
                    ArrayList<Book> arrayList = (ArrayList) StringSerialize.fromString(response.body());
                    return arrayList;
                }
                return new ArrayList<Book>();
            } catch(Exception e) {
                e.printStackTrace();
            }
            throw new BookStoreException("Could not read books from database");
        }, executorService);
        return future;
    }

    @Override
    public CompletableFuture<Optional<Book>> update(Book entity) throws ValidatorException {
        return null;
    }

    @Override
    public CompletableFuture<Optional<Book>> delete(Integer integer) {
        return null;
    }
    */
}
