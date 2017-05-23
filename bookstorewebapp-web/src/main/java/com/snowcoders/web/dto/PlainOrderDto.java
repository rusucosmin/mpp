package com.snowcoders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by cosmin on 13/05/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PlainOrderDto extends BaseDto {
    private Long clientid;
    private Long bookid;
    @Override
    public String toString() {
        return "Order{" +
                "bookId=" +bookid.toString() +
                ",clientId=" + clientid.toString() +
                "} " + super.toString();
    }
}
