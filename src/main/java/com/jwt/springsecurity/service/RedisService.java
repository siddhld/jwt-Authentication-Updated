package com.jwt.springsecurity.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper mapper;

    public <T> T get(String username, Class<T> entity) {
        try {
            System.err.println("-------  username  ------- "+ username + "------");
            System.err.println("-------  RedisService  ------- "+entity.toString());
            String jsonValue = redisTemplate.opsForValue().get(username);
            System.err.println("-------  RedisService  ------- " + jsonValue);
            return (jsonValue == null) ? null : mapper.readValue(jsonValue, entity);
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

//    In the Below Set method the token will be removed automatically from the Redis once it Expired,
//    Implement a logic In which it deletes from the SQL Db table also whenever in the Redis is deleted.

    public void set(String username, Object o, Long expTime) {
        try {
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(username, jsonValue, expTime, TimeUnit.SECONDS);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void delete(String username){
        try{
            redisTemplate.delete(username);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

}
