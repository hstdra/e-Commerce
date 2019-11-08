//package com.team.ecommerce.security;
//
//import com.team.ecommerce.service.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//@Order(1)
//public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    MyUserDetailsService authenticationService;
//
//    private BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler successHandler2() {
//        return new MyCustomLoginSuccessHandler("/web/login_success");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // account kai/admin1234
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("kai")
//                .password("$2a$04$PQtbtSAHZgBXLN.K/gZ3/eomQtZkB8R7x03KqZJxOTAbLRkxD9jgC").roles("ADMIN");
//
//        // account sena/123456
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("sena")
//                .password("$2a$04$Q2Cq0k57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("ADMIN");
//        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and()
//                .formLogin()
//                .loginProcessingUrl("/customer_login")
//                .loginPage("/web/login")
//                .successHandler(successHandler2())
//                .failureUrl("/web/login?message=error")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().exceptionHandling().accessDeniedPage("/403")
//                .and().logout().logoutUrl("/web/logout_success").clearAuthentication(true).invalidateHttpSession(true);

//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//    }
//
//}
