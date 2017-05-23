package com.snowcoders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by cosmin on 11/05/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDto extends BaseDto {
    private ClientDto client;
    private BookDto book;
    @Override
    public String toString() {
        return "Order{" +
                "book=" + book.toString() +
                ",client=" + client.toString() +
                "} " + super.toString();
    }
}
