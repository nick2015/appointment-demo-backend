package com.nick.TaroDemo.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.nick.TaroDemo.dto.LoginResponse;
import com.nick.TaroDemo.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private Cache<String, UserInfo> tokenCache;

    @Autowired
    private Cache<String, String> smsCodeCache;

    public void sendVerificationCode(String phone) {
        // TODO try to verify same phone num sent many times per day
        // generate sms code
        // String code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
        String code = "123456";

        smsCodeCache.put(phone, code);
        log.info("send sms code {} to phone {} ", code, phone);
    }

    public LoginResponse loginByPhone(String phone, String code) {
        // check sms code
        String savedCode = smsCodeCache.getIfPresent(phone);
        if (savedCode == null || !savedCode.equals(code)) {
            log.warn("sms code is expired or user did not send the sms code");
            return null;
        }
        smsCodeCache.invalidate(phone);

        // set token into cache
        String token = UUID.randomUUID().toString();
        UserInfo userInfo = new UserInfo(phone);
        tokenCache.put(token, userInfo);

        return new LoginResponse(token, userInfo.getUserId());
    }
}