package ro.ubb.bookstore.client.service;

import ro.ubb.bookstore.client.tcp.TcpClient;
import ro.ubb.bookstore.common.model.Message;
import ro.ubb.bookstore.common.model.Order;
import ro.ubb.bookstore.common.model.validators.BookStoreException;
import ro.ubb.bookstore.common.service.IBookService;
import ro.ubb.bookstore.common.service.IClientService;
import ro.ubb.bookstore.common.service.IOrderService;
import ro.ubb.bookstore.common.utils.StringSerialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class OrderServiceClient extends CRUDServiceClient<Integer, Order> implements IOrderService {
    private IBookService bookService;
    private IClientService clientService;
    public OrderServiceClient(IBookService bookService, IClientService clientService, ExecutorService executorService, TcpClient tcpClient) {
        super(executorService, tcpClient, "Order");
        this.bookService = bookService;
        this.clientService = clientService;
    }

    @Override
    public CompletableFuture<List<Map.Entry<Integer, Integer>>> getStatistics() {
        return CompletableFuture.supplyAsync( () -> {
            try {
                Message request = new Message(IOrderService.STATISTICS, "");
                Message response = tcpClient.sendAndReceive(request);
                if (response.header().equals(Message.OK)) {
                    ArrayList<Map.Entry<Integer, Integer>> arr = (ArrayList) StringSerialize.fromString(response.body());
                    return arr;
                }
                else
                    return new ArrayList<>();
            } catch (Exception e) {
                throw new BookStoreException(e.getMessage());
            }
        });
    }
}
