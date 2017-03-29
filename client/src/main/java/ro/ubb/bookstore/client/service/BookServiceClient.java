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
}
