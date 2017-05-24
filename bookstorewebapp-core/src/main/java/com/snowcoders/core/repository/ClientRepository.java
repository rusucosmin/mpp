package com.snowcoders.core.repository;

import com.snowcoders.core.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends BookstoreRepository<Client, Long>, ClientRepositoryCustom {

    @Query("select distinct s from Client s")
    @EntityGraph(value = "clientWithBooks", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithOrdersGraph();

    @Query("select distinct s from Client s where s.id=?1")
    @EntityGraph(value = "clientWithBooks", type = EntityGraph.EntityGraphType.LOAD)
    Client findOneWithOrders(Long studentId);
}
