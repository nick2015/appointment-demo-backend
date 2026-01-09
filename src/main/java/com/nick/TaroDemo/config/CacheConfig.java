package com.nick.TaroDemo.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.nick.TaroDemo.entity.UserInfo;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    // 1. token cache
    @Bean
    public Cache<String, UserInfo> tokenCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofHours(2))
                .maximumSize(10000)
                .build();
    }

    // 2. sms code cache
    @Bean
    public Cache<String, String> smsCodeCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(5)) // 验证码 5 分钟失效
                .maximumSize(5000)
                .build();
    }

}