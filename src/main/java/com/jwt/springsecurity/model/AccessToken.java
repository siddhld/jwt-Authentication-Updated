package com.jwt.springsecurity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String tokenKey;
    private String token;
    @Column(name = "expiration_time", nullable = false)
    private Timestamp expirationTime;

    public AccessToken(){}

    public AccessToken(String tokenKey, String token, Timestamp expirationTime) {
        this.tokenKey = tokenKey;
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public String getTokenKey(){
        return this.tokenKey;
    }

    public String getToken(){
        return this.token;
    }

    public void setTokenKey(String tokenKey){
        this.tokenKey = tokenKey;
    }

    public void setToken(String token){
        this.token = token;
    }
}
