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
public class OrderDto {
    private Long clientId;
    private Long bookId;
    private String bookTitle;
    private String clientName;
}
