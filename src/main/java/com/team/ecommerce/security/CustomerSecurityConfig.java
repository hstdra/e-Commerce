package com.team.ecommerce.security;

import com.team.ecommerce.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(2)
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService authenticationService;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cấu hình cho Login Form.
        http.authorizeRequests().antMatchers("/user/**").hasRole("USER").and()
                .formLogin()//
                .loginProcessingUrl("/j_spring_security_login")//
                .loginPage("/login2")//
                .defaultSuccessUrl("/user")//
                .failureUrl("/login2?message=error")//
                .usernameParameter("email").passwordParameter("password")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login2?message=logout");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }
}
