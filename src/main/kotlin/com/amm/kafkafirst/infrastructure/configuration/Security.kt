package com.amm.kafkafirst.infrastructure.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebSecurity
@EnableWebMvc
class Security : WebSecurityConfigurerAdapter() {

    private val logger = LoggerFactory.getLogger(Security::class.java)

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        logger.info("configure security...")
        http.authorizeRequests()
            .anyRequest().permitAll()
            .and().csrf().disable()
    }

    override fun configure(web: WebSecurity) {
        logger.info("configure WebSecurity...")
        web.ignoring().antMatchers(*HttpParams.URI_AUTH_WHITELIST)
    }

}
