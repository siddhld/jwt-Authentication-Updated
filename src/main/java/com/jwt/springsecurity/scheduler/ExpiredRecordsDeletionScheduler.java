package com.jwt.springsecurity.scheduler;

import com.jwt.springsecurity.repository.AccessTokenRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class ExpiredRecordsDeletionScheduler {

    @Autowired
    private AccessTokenRepo accessTokenRepo;

    // 16 minutes
    private final long jwtAccessTokenExpiry = 1000 * 60 * 16;

    @Scheduled(fixedRate = jwtAccessTokenExpiry)
    @Transactional
    public void deleteExpiredRecords() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        accessTokenRepo.deleteByExpirationTimeBefore(now);
    }
}
