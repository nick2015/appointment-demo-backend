package com.nick.TaroDemo.util;

public class SimpleException extends RuntimeException{

    private String status;

    public SimpleException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.status = resultEnum.getStatus();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
