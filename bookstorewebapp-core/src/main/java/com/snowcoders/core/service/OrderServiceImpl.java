package com.snowcoders.core.service;

import com.snowcoders.core.model.Book;
import com.snowcoders.core.model.Client;
import com.snowcoders.core.model.Order;
import com.snowcoders.core.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    protected OrderRepository orderRepository;

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order createOrder(Client client, Book book) {
        Order order = new Order(client, book);
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    @Transactional
    public Order updateOrder(Long id, Client client, Book book) {
        Order order = orderRepository.findOne(id);
        order.setClient(client);
        order.setBook(book);
        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.delete(id);
    }
}
