package com.snowcoders.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
@IdClass(OrderPK.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="clientId")
    private Client client;

    @Id
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="bookId")
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getClient() != null ? !getClient().equals(order.getClient()) : order.getClient() != null) return false;
        return getBook() != null ? getBook().equals(order.getBook()) : order.getBook() == null;

    }

    @Override
    public int hashCode() {
        int result = getClient() != null ? getClient().hashCode() : 0;
        result = 31 * result + (getBook() != null ? getBook().hashCode() : 0);
        return result;
    }
}
