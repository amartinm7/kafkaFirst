package com.amm.kafkafirst.infrastructure.configuration

object HttpParams {
    val URL_CONSUMER = "/consumer"
    val URL_PROVIDER = "/provider"

    val URI_AUTH_WHITELIST = arrayOf(
        URL_PROVIDER,
        URL_CONSUMER,
        "/",
        "/indexNew.html",
        "/actuator/**",
        "/src/**",
        "/v1/**",
        "/v2/**",
        "/v3/**",
        "/index.html",
        "/images/**",
        "/api/**",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**"
    )

    val X_PASSWORD = "X-Password"
    val USER_NAME = "username"
}
