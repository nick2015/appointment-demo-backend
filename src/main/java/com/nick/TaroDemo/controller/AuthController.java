package com.nick.TaroDemo.controller;

import com.nick.TaroDemo.NoAuthCheck;
import com.nick.TaroDemo.dto.LoginRequest;
import com.nick.TaroDemo.dto.LoginResponse;
import com.nick.TaroDemo.dto.ResultEntity;
import com.nick.TaroDemo.service.AuthService;
import com.nick.TaroDemo.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/sendCode")
    @NoAuthCheck
    public ResponseEntity<String> sendCode(@RequestParam("phoneNumber") String phoneNumber) {
        //TODO try to block same ip requesting for many times per day
        authService.sendVerificationCode(phoneNumber);
        return ResponseEntity.ok("验证码已发送");
    }

    @PostMapping("/login")
    @NoAuthCheck
    public ResultEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
        LoginResponse response = authService.loginByPhone(request.getPhoneNumber(), request.getSmsCode());
        if (response != null) {
            return new ResultEntity<>(response);
        }
        return new ResultEntity<>(ResultEnum.SMS_CODE_EXPIRED);
    }
}