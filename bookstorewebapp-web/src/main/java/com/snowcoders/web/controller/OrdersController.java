package com.snowcoders.web.controller;

import com.snowcoders.core.model.Client;
import com.snowcoders.core.model.Order;
import com.snowcoders.core.service.ClientService;
import com.snowcoders.web.converter.OrderConverter;
import com.snowcoders.web.dto.EmptyJsonResponse;
import com.snowcoders.web.dto.OrderDto;
import com.snowcoders.web.dto.OrdersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class OrdersController {

    @Autowired
    protected ClientService clientService;
    @Autowired
    protected OrderConverter orderConverter;

    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public OrdersDto getOrders() {
        log.trace("OrdersController::getOrders()");
        List<Client> clients = clientService.findAll();
        log.trace(clients.toString());
        Set<Order>  orders = clients.stream().map(c -> c.getOrders()).flatMap(c -> c.stream())
                .collect(Collectors.toSet());
        log.trace(orders.toString());
        Set<OrderDto> orderDtoSet = orderConverter.convertModelsToDtos(orders);

        return new OrdersDto(orderDtoSet);
    }

    @RequestMapping(value = "/orders/{clientId}/{bookId}", method = RequestMethod.GET)
    public OrderDto getOrder(@PathVariable final Long clientId,
                                    @PathVariable final Long bookId) {
        log.trace("OrdersController::getOrder()");
        log.trace("clientId = " + clientId);
        log.trace("bookId = " + bookId);
        Client client = clientService.findOne(clientId);

        Order order = client.getOrders()
                .stream()
                .filter(o -> o.getBook().getId().equals(bookId))
                .findFirst().get();
        OrderDto orderDto = orderConverter.convertModelToDto(order);

        return orderDto;
    }

    @RequestMapping(value="/orders/{clientId}/{bookId}", method = RequestMethod.PUT)
    public OrderDto updateOrder(
            @PathVariable final Long clientId,
            @PathVariable final Long bookId,
            @RequestBody final OrderDto orderDto) {
        log.trace("OrdersController::updateOrder()");
        Client oldClient = clientService.findOne(clientId);
        oldClient = clientService.updateClient(clientId, oldClient.getName(), oldClient.getOrders()
                .stream()
                .map(o -> o.getBook().getId())
                .filter(bid -> !bid.equals(bookId))
                .collect(Collectors.toSet())
        );
        Client newClient = clientService.findOne(orderDto.getClientId());
        Set<Long> bookIds = newClient.getOrders()
                .stream()
                .map(o -> o.getBook().getId())
                .collect(Collectors.toSet());
        bookIds.add(orderDto.getBookId());
        newClient = clientService.updateClient(orderDto.getClientId(), newClient.getName(), bookIds);
        return orderConverter.convertModelToDto(newClient.getOrders().stream()
                .filter(o->o.getBook().getId().equals(orderDto.getBookId()))
                .findFirst()
                .get()
        );
    }

    @RequestMapping(value="/orders", method = RequestMethod.POST)
    public OrderDto createOrder(@RequestBody final OrderDto orderDto) {
        log.trace("create order, orderDto = {}", orderDto);
        Client client = clientService.findOne(orderDto.getClientId());
        Set<Long> bookIds = client.getOrders().stream().map(o -> o.getBook().getId()).collect(Collectors.toSet());
        if(!bookIds.contains(orderDto.getBookId()))
            bookIds.add(orderDto.getBookId());
        client = clientService.updateClient(orderDto.getClientId(), orderDto.getClientName(), bookIds);
        OrderDto result = orderConverter.convertModelToDto(client.getOrders()
                .stream()
                .filter(o ->
                        o.getBook().getId().equals(orderDto.getBookId()))
                .findFirst()
                .get());
        log.trace("result: " + result.toString());
        return result;
    }

    @RequestMapping(value = "/orders/{clientId}/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable final Long clientId,
                                      @PathVariable final Long bookId) {
        log.trace("delete order");
        Client client = clientService.findOne(clientId);
        log.trace("update client = {}", client);
        client = clientService.updateClient(clientId, client.getName(), client.getBooks()
                .stream()
                .map(o -> o.getId())
                .filter(bid -> !bid.equals(bookId))
                .collect(Collectors.toSet()));
        log.trace("updated client = {}", client);
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
