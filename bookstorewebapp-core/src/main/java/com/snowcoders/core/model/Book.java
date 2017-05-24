package com.snowcoders.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends BaseEntity<Long> {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Order> orders  = new HashSet<>();

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(
                orders.stream()
                    .map(o -> o.getClient())
                    .collect(Collectors.toSet())
        );
    }

    public void addClient(Client client) {
        Order order = new Order();
        order.setBook(this);
        order.setClient(client);
        this.orders.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!getTitle().equals(book.getTitle())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        return getPrice().equals(book.getPrice());

    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title=" + this.getTitle() +
                ",author=" + this.getAuthor() +
                ",price=" + this.getPrice() +
                "}, " + super.toString();
    }
}
