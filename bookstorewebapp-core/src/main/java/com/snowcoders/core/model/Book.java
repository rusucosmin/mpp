package com.snowcoders.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book extends BaseEntity<Long> {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "price", nullable = false)
    private Double price;
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
//    private Set<Order> orders;
}
