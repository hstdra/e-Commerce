package com.team.ecommerce.security;

import com.team.ecommerce.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Order(2)
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService authenticationService;

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new MyCustomLoginSuccessHandler("/web/login_success");
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/web/cart/**").hasAnyRole("CUSTOMER", "ADMIN").and()
                .formLogin()
                .loginProcessingUrl("/customer_login")
                .loginPage("/web/login")
                .successHandler(successHandler())
                .failureUrl("/web/login?message=error")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().logout().logoutUrl("/web/logout_success").clearAuthentication(true).invalidateHttpSession(true);

        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and()
                .formLogin()
                .loginProcessingUrl("/customer_login")
                .loginPage("/web/login")
                .successHandler(successHandler())
                .failureUrl("/web/login?message=error")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().logout().logoutUrl("/web/logout_success").clearAuthentication(true).invalidateHttpSession(true);


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }
}
