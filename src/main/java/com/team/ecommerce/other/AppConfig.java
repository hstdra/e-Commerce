package com.team.ecommerce.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean("vncurrency")
    VnCurrency vnCurrency() {
        return new VnCurrency();
    }
}
