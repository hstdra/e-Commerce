package com.team.ecommerce.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;

@Configuration
public class AppConfig {

    @PersistenceContext
    EntityManager entityManager;

    @Bean("vncurrency")
    VnCurrency vnCurrency() {
        return new VnCurrency();
    }

    @Bean("dateFormat")
    SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    @Bean
    EntityManager entityManager() {
        return entityManager;
    }
}
