package com.snowcoders.web.converter;

import com.snowcoders.core.model.Order;
import com.snowcoders.web.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends BaseConverter<Order, OrderDto> {
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private ClientConverter clientConverter;
    @Override
    public Order convertDtoToModel(OrderDto dto) {
        Order order = new Order(clientConverter.convertDtoToModel(dto.getClient()), bookConverter.convertDtoToModel(dto.getBook()));
        order.setId(dto.getId());
        return order;
    }

    @Override
    public OrderDto convertModelToDto(Order order) {
        OrderDto orderDto = new OrderDto(clientConverter.convertModelToDto(order.getClient()), bookConverter.convertModelToDto(order.getBook()));
        orderDto.setId(order.getId());
        return orderDto;
    }
}
