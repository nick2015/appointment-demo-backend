package com.nick.TaroDemo.dto;

import com.nick.TaroDemo.entity.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends UserInfo {

    public LoginResponse(String userId) {
        super(userId);
    }

    public LoginResponse(String token, String userId) {
        this.token = token;
        this.setUserId(userId);
    }

    private String token;

}
