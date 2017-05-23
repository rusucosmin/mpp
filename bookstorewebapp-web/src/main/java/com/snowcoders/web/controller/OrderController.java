package com.snowcoders.web.controller;

import com.snowcoders.core.model.Order;
import com.snowcoders.core.service.BookService;
import com.snowcoders.core.service.ClientService;
import com.snowcoders.core.service.OrderService;
import com.snowcoders.web.converter.OrderConverter;
import com.snowcoders.web.converter.PlainOrderConverter;
import com.snowcoders.web.dto.EmptyJsonResponse;
import com.snowcoders.web.dto.OrderDto;
import com.snowcoders.web.dto.OrdersDto;
import com.snowcoders.web.dto.PlainOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    protected OrderService orderService;
    @Autowired
    protected BookService bookService;
    @Autowired
    protected ClientService clientService;
    @Autowired
    protected OrderConverter orderConverter;
    @Autowired
    protected PlainOrderConverter plainOrderConverter;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public OrdersDto getAll() {
        return new OrdersDto(orderService.findAll());
    }

    @RequestMapping(value="/orders/{orderId}", method = RequestMethod.PUT)
    public Map<String, OrderDto> updateOrder(
            @PathVariable final Long orderId,
            @RequestBody final Map<String, PlainOrderDto> orderDtoMap) {

        PlainOrderDto orderDto = orderDtoMap.get("order");
        Order order = orderService.updateOrder(orderId,
                clientService.findOne(orderDto.getClientid()),
                bookService.findOne(orderDto.getBookid()));

        Map<String, OrderDto> result = new HashMap<>();
        result.put("order", orderConverter.convertModelToDto(order));

        return result;
    }

    @RequestMapping(value="/orders", method = RequestMethod.POST)
    public Map<String, OrderDto> createOrder(@RequestBody final Map<String, PlainOrderDto> orderDtoMap) {
        PlainOrderDto orderDto = orderDtoMap.get("order");
        Order order = orderService.createOrder(clientService.findOne(orderDto.getClientid()),
                bookService.findOne(orderDto.getBookid()));

        Map<String, OrderDto> result = new HashMap<>();
        result.put("order", orderConverter.convertModelToDto(order));

        return result;
    }

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable final Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
