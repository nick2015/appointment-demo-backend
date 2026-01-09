package com.nick.TaroDemo.util;

public enum ResultEnum {
    // system message
    SUCCESS("0000", "Success"),
    LOGIN_ERROR("0001", "Login Expired"),
    PARAM_ERROR("0002", "Error params"),
    SMS_CODE_EXPIRED("0003", "SMS Code has expired, please send the message again"),
    EXCEPTION("9999", "System Error"),

    // appointment message
    TIME_CONFLICT("1001", "appointment time is conflicted"),
    ;

    private String status;

    private String msg;

    ResultEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static ResultEnum getEnumKey(String key) {
        for (ResultEnum e : ResultEnum.values()) {
            if (e.getStatus().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
