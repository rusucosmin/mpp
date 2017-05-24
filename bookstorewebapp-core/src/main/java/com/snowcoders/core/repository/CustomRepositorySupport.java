package com.snowcoders.core.repository;

import com.snowcoders.core.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by cosmin on 23/05/2017.
 */
@Getter
@Setter
public abstract class CustomRepositorySupport<T extends BaseEntity<ID>, ID extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

}
