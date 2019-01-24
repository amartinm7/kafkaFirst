package com.amm.kafkafirst.infrastructure;

public class HttpParams {
    public static final String URL_CONSUMER                 = "/consumer";
    public static final String URL_PROVIDER                 = "/provider";

    static final String[] URI_AUTH_WHITELIST = {
            URL_PROVIDER, URL_CONSUMER,
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
            "/webjars/**"};

    public static final String X_PASSWORD                   = "X-Password";
    public static final String USER_NAME                    = "username";
}
