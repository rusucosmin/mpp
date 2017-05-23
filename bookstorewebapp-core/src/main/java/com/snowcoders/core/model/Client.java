package com.snowcoders.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client extends BaseEntity<Long> {
    @Column(name = "name", nullable = false)
    private String name;
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
//    private Set<Order> orders;
}
