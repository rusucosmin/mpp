package com.snowcoders.core.service;

import com.snowcoders.core.model.Book;
import com.snowcoders.core.model.Client;
import com.snowcoders.core.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order createOrder(Client client, Book book);
    Order findOne(Long id);
    Order updateOrder(Long id, Client client, Book book);
    void deleteOrder(Long id);
}
