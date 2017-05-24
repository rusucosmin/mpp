package com.snowcoders.web.converter;

import com.snowcoders.core.model.Order;
import com.snowcoders.web.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends BaseConverterGeneric<Order, OrderDto> {
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private ClientConverter clientConverter;
    @Override
    public Order convertDtoToModel(OrderDto dto) {
        throw new RuntimeException("here is your problem!");
    }

    @Override
    public OrderDto convertModelToDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .bookId(order.getBook().getId())
                .clientId(order.getClient().getId())
                .bookTitle(order.getBook().getTitle())
                .clientName(order.getClient().getName())
                .build();
        return orderDto;
    }
}
