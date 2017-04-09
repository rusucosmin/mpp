package ro.ubb.c04remotingjdbctemplate.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.c04remotingjdbctemplate.common.Order;
import ro.ubb.c04remotingjdbctemplate.common.OrderService;

import java.util.List;

/**
 * Created by Petru on 4/5/2017.
 */
public class OrderServiceClient {
    @Autowired
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Order> getOrders() { return orderService.getOrders(); }

    public void addOrder(Order c) {
        orderService.addOrder(c);
    }

    public void updateOrder(Order c) {
        orderService.updateOrder(c);
    }

    public void deleteOrder(Integer id) { orderService.deleteOrder(id); }
}
