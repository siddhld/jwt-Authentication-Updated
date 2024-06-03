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
            String jsonValue = redisTemplate.opsForValue().get(username);
            return (jsonValue == null) ? null : mapper.readValue(jsonValue, entity);
        } catch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

//    Can you Implement a logic where a record will be deleted automatically after specific given time in MySql

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
