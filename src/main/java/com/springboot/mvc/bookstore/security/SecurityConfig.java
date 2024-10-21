package com.springboot.mvc.bookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    private final String[] publicUrls={"/store/home",
            "/store/recent","/store/best-seller","/store/top-rated","/store/most-popular",
            "/store/book/{bookId}","/*.css","/register","/save"};
    @Bean
    public UserDetailsManager detailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT email, password,enabled FROM User WHERE email=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT email,role FROM User WHERE email=?"
        );
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configure->{
                    configure.requestMatchers(publicUrls).permitAll();
                    configure.requestMatchers("/book/**").hasRole("ADMIN");
                    configure.anyRequest().authenticated();
                })
                .formLogin(login->
                        login
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/store/home",true)
                                .permitAll())
                .logout(logout->logout.permitAll())
                .exceptionHandling(configure->
                        configure
                                .accessDeniedPage("/access-denied")
                )
        ;

        return http.build();
    }
}
