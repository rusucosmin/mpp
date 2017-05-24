package com.snowcoders.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithBooks", attributeNodes = {
                @NamedAttributeNode(value = "orders", subgraph = "ordersGraph")
        }, subgraphs = {
                @NamedSubgraph(name = "ordersGraph", attributeNodes = {
                        @NamedAttributeNode(value = "book")
                })
        }
        )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Client extends BaseEntity<Long> {
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Order> orders = new HashSet<>();

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(
            this.orders.stream()
                .map(o -> o.getBook())
                .collect(Collectors.toSet())
        );
    }

    public void addBook(Book book) {
        Order order = new Order();
        order.setBook(book);
        order.setClient(this);
        this.orders.add(order);
    }

    public void addBooks(Set<Book> books) {
        books.stream()
                .forEach(book -> this.addBook(book));
    }

    public void removeBook(Book book) {
        Optional<Order> order = this.orders
                .stream()
                .filter(o -> o.getBook().equals(book))
                .findFirst();
        if(order.isPresent())
            this.orders.remove(order.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return getName().equals(client.getName());
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", orders=" + orders +
                '}' + super.toString();
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        return result;
    }
}
