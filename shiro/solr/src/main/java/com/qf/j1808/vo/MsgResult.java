package com.qf.j1808.vo;
//自定义消息对象
import lombok.Data;

@Data
public class MsgResult {
    private int status;//状态码
    private String message;//短消息
    private Object data;//携带的对象

}
