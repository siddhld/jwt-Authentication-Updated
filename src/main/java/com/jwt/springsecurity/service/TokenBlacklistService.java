package com.jwt.springsecurity.service;

import com.jwt.springsecurity.model.AccessToken;
import com.jwt.springsecurity.repository.AccessTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TokenBlacklistService {

    @Autowired
    private RedisService redisService;

    @Value("${jwt.access.token.expiry}")
    private long jwtAccessTokenExpiryInMillisecond;

    @Value("${jwt.access.token.expiry.second}")
    private long jwtAccessTokenExpiryInSecond;

    @Autowired
    private AccessTokenRepo accessTokenRepo;

    @Transactional(rollbackFor = {Exception.class})
    public void blacklistToken(String token) {
        System.err.println("------******** Inside blacklistToken");
        try {

            // Setting the Expiration time for access token (current time + 15 minutes in millisecond)
            long expirationTime = System.currentTimeMillis() + jwtAccessTokenExpiryInMillisecond;

            accessTokenRepo.save(new AccessToken(token, token, new Timestamp(expirationTime)));
            redisService.set("access-token-" + token, token, jwtAccessTokenExpiryInSecond);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean isTokenBlacklisted(String token) {

        System.err.println("--- Checking Redis --- "+redisService.get("name", String.class));

        if (redisService.get("access-token-" + token, String.class) == null) {
            System.err.println("===Data is not Present in Redis===");
            Optional<AccessToken> accessToken = accessTokenRepo.findByTokenKey(token);

            if (accessToken.isPresent()) {
                redisService.set("access-token-" + token, token, jwtAccessTokenExpiryInSecond);
                return true;
            }
            System.err.println("===Data is not Present in SQL===");
            return false;
        }
        return true;
    }
}
