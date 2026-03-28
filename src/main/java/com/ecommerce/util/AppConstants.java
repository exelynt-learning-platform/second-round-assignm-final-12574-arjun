package com.ecommerce.util;


public class AppConstants {

    //  JWT
    public static final String SECRET_KEY = "mysecretkey1234567890";
    public static final long JWT_EXPIRATION = 1000 * 60 * 60; // 1 hour

    // HEADER
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // ROLES
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    // API PREFIX
    public static final String API_PREFIX = "/api/v1";
}