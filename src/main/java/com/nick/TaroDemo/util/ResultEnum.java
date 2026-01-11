package com.nick.TaroDemo.util;

public enum ResultEnum {
    // system message
    SUCCESS("0000", "Success"),
    LOGIN_ERROR("0001", "登录已过期"),
    SMS_CODE_ERROR("0002", "验证码错误"),
    SMS_CODE_EXPIRED("0003", "请先发送验证码"),
    PARAM_ERROR("0009", "请求参数有误"),
    EXCEPTION("9999", "系统错误！"),

    // appointment message
    TIME_CONFLICT("1001", "该时间段存在预约，请确认后修改时间！"),
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
