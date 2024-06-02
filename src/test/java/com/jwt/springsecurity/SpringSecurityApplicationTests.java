package com.jwt.springsecurity;

import com.jwt.springsecurity.service.RedisService;
import com.jwt.springsecurity.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	private RedisService redisService;
	@Test
	void contextLoads() {
		System.err.println(RedisUtils.redisExpAccessToken);
		redisService.set("kala","kalusingh@gmail.com", RedisUtils.redisExpAccessToken);
		String email = redisService.get("kala", String.class);
		System.err.println(email);
	}

}
