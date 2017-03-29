package ro.ubb.bookstore.client;

import ro.ubb.bookstore.client.service.BookServiceClient;
import ro.ubb.bookstore.client.service.ClientServiceClient;
import ro.ubb.bookstore.client.service.OrderServiceClient;
import ro.ubb.bookstore.client.tcp.TcpClient;
import ro.ubb.bookstore.client.view.Console;
import ro.ubb.bookstore.common.service.IBookService;
import ro.ubb.bookstore.common.service.IClientService;
import ro.ubb.bookstore.common.service.IOrderService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TcpClient tcpClient = new TcpClient("localhost", 1234);
        IBookService bookService = new BookServiceClient(executorService, tcpClient);
        IClientService clientService = new ClientServiceClient(executorService, tcpClient);
        IOrderService orderService = new OrderServiceClient(bookService, clientService, executorService, tcpClient);
        Console console = new Console(bookService, clientService, orderService);
        console.run();

        executorService.shutdownNow();
    }
}
