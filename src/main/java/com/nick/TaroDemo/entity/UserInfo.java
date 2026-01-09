package com.nick.TaroDemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {

    public UserInfo(String userId) {
        this.userId = userId;
    }

    private String userId;

    private String userName;
}
