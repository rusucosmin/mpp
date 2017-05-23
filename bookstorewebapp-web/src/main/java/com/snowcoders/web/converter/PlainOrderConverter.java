package com.snowcoders.web.converter;

import com.snowcoders.core.model.Order;
import com.snowcoders.core.service.BookService;
import com.snowcoders.core.service.ClientService;
import com.snowcoders.web.dto.PlainOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cosmin on 13/05/2017.
 */
@Component
public class PlainOrderConverter extends BaseConverter<Order, PlainOrderDto> {
    @Autowired
    ClientService clientService;
    @Autowired
    BookService bookService;
    @Override
    public Order convertDtoToModel(PlainOrderDto dto) {
        Order order = new Order(clientService.findOne(dto.getClientid()), bookService.findOne(dto.getBookid()));
        order.setId(dto.getId());
        return order;
    }

    @Override
    public PlainOrderDto convertModelToDto(Order order) {
        PlainOrderDto plainOrderDto = new PlainOrderDto(order.getClient().getId(), order.getBook().getId());
        plainOrderDto.setId(order.getId());
        return plainOrderDto;
    }
}
