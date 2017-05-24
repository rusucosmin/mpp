package com.snowcoders.core.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by cosmin on 23/05/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderPK implements Serializable {
    private Book book;
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPK)) return false;

        OrderPK orderPK = (OrderPK) o;

        if (getBook() != null ? !getBook().equals(orderPK.getBook()) : orderPK.getBook() != null) return false;
        return getClient() != null ? getClient().equals(orderPK.getClient()) : orderPK.getClient() == null;

    }

    @Override
    public int hashCode() {
        int result = getBook() != null ? getBook().hashCode() : 0;
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        return result;
    }
}
