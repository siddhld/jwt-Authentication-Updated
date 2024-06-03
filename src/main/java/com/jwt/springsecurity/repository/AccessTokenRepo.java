package com.jwt.springsecurity.repository;

import com.jwt.springsecurity.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface AccessTokenRepo extends JpaRepository<AccessToken, Integer> {
    public Optional<AccessToken> findByTokenKey(String tokenKey);
    void deleteByExpirationTimeBefore(Timestamp now);
}
