package com.snowcoders.core.repository;

import com.snowcoders.core.model.Client;

import java.util.List;

/**
 * Created by cosmin on 23/05/2017.
 */
public interface ClientRepositoryCustom {
    List<Client> findAllWithOrdersSqlQuery();

    List<Client> findAllWithOrdersJpql();

    List<Client> findAllWithOrdersJpaCriteria();
}
