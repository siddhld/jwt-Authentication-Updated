package com.jwt.springsecurity.utils;

public class RedisUtils {
    public static final long redisExpAccessToken = 60 * 15;
    public static final long redisExpRefreshToken = 60 * 60 * 24 * 7;
}

