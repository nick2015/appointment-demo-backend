package com.nick.TaroDemo.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String phoneNumber;

    private String smsCode;
}
