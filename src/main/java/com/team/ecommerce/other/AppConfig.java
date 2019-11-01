package com.team.ecommerce.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;

@Configuration
public class AppConfig {
    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }


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
