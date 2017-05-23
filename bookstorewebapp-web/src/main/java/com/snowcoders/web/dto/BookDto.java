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
public class BookDto extends BaseDto {
    private String title;
    private String author;
    private Double price;
    @Override
    public String toString() {
        return "BookDto{" +
                "title=" + title +
                ", author=" + author +
                ", price=" + String.valueOf(price) +
                "} " + super.toString();

    }
}
