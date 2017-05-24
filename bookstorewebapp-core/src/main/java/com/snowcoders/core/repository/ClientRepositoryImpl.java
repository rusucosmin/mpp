package com.snowcoders.core.repository;

import com.snowcoders.core.model.Client;
import com.snowcoders.core.model.Client_;
import com.snowcoders.core.model.Order;
import com.snowcoders.core.model.Order_;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by cosmin on 23/05/2017.
 */
public class ClientRepositoryImpl extends CustomRepositorySupport<Client, Long> implements ClientRepositoryCustom {
        private static final Logger log = LoggerFactory.getLogger(ClientRepositoryImpl.class);

        @Override
        @Transactional
        public List<Client> findAllWithOrdersSqlQuery() {
            log.trace("findAllWithOrdersSqlQuery: method entered");

            HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
            Session session = hibernateEntityManager.getSession();

            Query query = session.createSQLQuery("select distinct {c.*}, {o.*}, {b.*}" +
                    " from clients c" +
                    " left join orders o on o.clientId = c.id" +
                    " left join books b on b.id = o.bookId")
                    .addEntity("c", Client.class)
                    .addJoin("o", "c.orders")
                    .addJoin("b", "o.book")
                    .addEntity("c", Client.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Client> clients = query.list();

            log.trace("findAllWithOrdersSqlQuery: clients={}", clients);
            return clients;
        }

        @Override
        @Transactional
        public List<Client> findAllWithOrdersJpql() {
            log.trace("findAllWithClientsJpql: method entered");

            javax.persistence.Query query = getEntityManager().createQuery("select distinct c from Client c" +
                    " left join fetch c.orders o" +
                    " left join fetch o.book b");

            List<Client> clients = query.getResultList();

            log.trace("findAllWithClientsJpql: clients={}", clients);
            return clients;
        }



        @Override
        @Transactional
        public List<Client> findAllWithOrdersJpaCriteria() {
            log.trace("findAllWithOrdersJpaCriteria: method entered");

            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);

            query.distinct(Boolean.TRUE);

            Root<Client> from = query.from(Client.class);

            Fetch<Client, Order> clientOrderFetch = from.fetch(Client_.orders, JoinType.LEFT);
            clientOrderFetch.fetch(Order_.book, JoinType.LEFT);

            List<Client> clients = getEntityManager().createQuery(query).getResultList();

            log.trace("findAllWithDisciplinesJpaCriteria: students={}", clients);
            return clients;
        }
}
