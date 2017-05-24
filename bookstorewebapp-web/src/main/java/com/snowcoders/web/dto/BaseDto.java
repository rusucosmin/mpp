package com.snowcoders.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by cosmin on 11/05/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Long id;
}
