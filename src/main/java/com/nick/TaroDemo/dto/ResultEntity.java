package com.nick.TaroDemo.dto;

import com.nick.TaroDemo.util.ResultEnum;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class ResultEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String state;
    private String msg;
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String status, String msg) {
        this.state = status;
        this.msg = msg;
    }

    public ResultEntity(String status, String msg, T data) {
        this.state = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultEntity(ResultEnum resultEnum){
        this.state = resultEnum.getStatus();
        this.msg = resultEnum.getMsg();
    }

    public ResultEntity(T data){
        this.state = ResultEnum.SUCCESS.getStatus();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultEntity(ResultEnum resultEnum, T data){
        this.state = resultEnum.getStatus();
        this.msg = resultEnum.getMsg();
        this.data = data;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
