package com.jwt.springsecurity.service;

import com.jwt.springsecurity.model.AccessToken;
import com.jwt.springsecurity.repository.AccessTokenRepo;
import com.jwt.springsecurity.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenBlacklistService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private AccessTokenRepo accessTokenRepo;

    @Transactional(rollbackFor = {Exception.class})
    public void blacklistToken(String token) {
        System.err.println("------******** Inside blacklistToken");
        try {
            accessTokenRepo.save(new AccessToken(token, token));
            redisService.set("access-token-" + token, token, RedisUtils.redisExpAccessToken);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public boolean isTokenBlacklisted(String token) {
        System.err.println("-----" + token + "-------- 1");
        System.err.println("------******** Inside isBlacklistToken " + redisService.get("access-token-" + token, String.class));
        System.err.println(redisService.get("access-token-" + token, String.class) == (token) ? true : false);

        if (redisService.get("access-token-" + token, String.class) == null) {
            AccessToken accessToken = null;
            if (accessTokenRepo.findByTokenKey(token).isPresent()) {
                accessToken = accessTokenRepo.findByTokenKey(token).get();
            }
            System.err.println("-----" + accessToken + "-------- 2");
            if (accessToken != null) {
                redisService.set("access-token-" + token, token, RedisUtils.redisExpAccessToken);
                return true;
            }
            return false;
        }
        return true;
    }
}
