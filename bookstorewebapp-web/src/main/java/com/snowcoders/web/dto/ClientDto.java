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
@Getter
@Setter
public class ClientDto extends BaseDto {
    private String name;
    @Override
    public String toString() {
        return "Client{" +
                "name=" + this.name +
                "} " + super.toString();
    }
}
