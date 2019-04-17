package com.genrs.webdataservice.security.constants;

public class WebSecurityConstants {

    public static String SIGN_UP_URL = "/api/v0/register/";
    public static String HEADER_STRING = "Authorization";
    public static final String SECRET = "DifferentSecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ROLE_PREFIX = "ROLE_";
}
