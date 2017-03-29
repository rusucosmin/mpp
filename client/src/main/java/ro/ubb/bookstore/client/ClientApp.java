package ro.ubb.bookstore.client;

import ro.ubb.bookstore.client.tcp.TcpClient;
import ro.ubb.bookstore.client.view.Console;
import ro.ubb.bookstore.common.service.IBookService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TcpClient tcpClient = new TcpClient("localhost", 1234);
        IBookService bookService = new BookServiceClient(executorService, tcpClient);
        Console console = new Console(bookService);
        console.run();

        executorService.shutdownNow();
    }
}
