package ro.ubb.c04remotingjdbctemplate.common;

import java.util.List;

/**
 * Created by Petru on 4/5/2017.
 */
public interface OrderService {
    void addOrder(Order o);
    List<Order> getOrders();
    void updateOrder(Order o);
    void deleteOrder(Integer id);
}
