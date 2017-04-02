package ro.ubb.bookstore.common.service;

import ro.ubb.bookstore.common.model.Order;

import java.util.concurrent.CompletableFuture;

public interface IOrderService extends ICRUDService<Integer, Order> {
    String CREATE = "createOrder";
    String READ = "readOrder";
    String READ_ALL = "readAllOrders";
    String UPDATE = "updateOrder";
    String DELETE = "deleteOrder";
    String STATISTICS = "statisticsOrders";
    CompletableFuture getStatistics();
}
