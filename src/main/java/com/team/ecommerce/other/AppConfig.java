package com.team.ecommerce.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class AppConfig {

    @PersistenceContext
    EntityManager entityManager;

    @Bean("vncurrency")
    VnCurrency vnCurrency() {
        return new VnCurrency();
    }

    @Bean
    EntityManager entityManager() {
        return entityManager;
    }


}
