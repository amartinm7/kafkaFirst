package com.amm.kafkafirst.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class Security extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(Security.class);


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("configure security...");
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        logger.info("configure WebSecurity...");
        web.ignoring().antMatchers(HttpParams.URI_AUTH_WHITELIST);
    }
}
