package com.snowcoders.web.dto;

import lombok.*;

import java.util.Set;

/**
 * Created by cosmin on 11/05/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClientDto extends BaseDto {
    private String name;
    private Set<Long> books;
}
