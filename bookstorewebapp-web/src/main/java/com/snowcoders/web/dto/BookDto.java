package com.snowcoders.web.dto;

import lombok.*;

/**
 * Created by cosmin on 11/05/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BookDto extends BaseDto {
    private String title;
    private String author;
    private Double price;
}
