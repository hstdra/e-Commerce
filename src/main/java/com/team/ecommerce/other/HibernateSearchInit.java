package com.team.ecommerce.other;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class HibernateSearchInit implements ApplicationListener<ContextRefreshedEvent> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//        try {
//            fullTextEntityManager.createIndexer().startAndWait();
//        } catch (InterruptedException e) {
//            System.out.println("Error occured trying to build Hibernate Search indexes "
//                    + e.toString());
//        }
    }
}
