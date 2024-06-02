package com.jwt.springsecurity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class AccessToken {

    @Id
    private int id;
    @Column(unique = true)
    private String tokenKey;
    private String token;

    public AccessToken(String tokenKey, String token) {
        this.tokenKey = tokenKey;
        this.token = token;
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
