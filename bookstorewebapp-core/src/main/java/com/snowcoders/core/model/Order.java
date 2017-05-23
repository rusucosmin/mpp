package com.snowcoders.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order extends BaseEntity<Long> {
    @ManyToOne(optional=false)
    @JoinColumn(name="clientId")
    private Client client;

    @ManyToOne(optional=false)
    @JoinColumn(name="bookId")
    private Book book;
}
