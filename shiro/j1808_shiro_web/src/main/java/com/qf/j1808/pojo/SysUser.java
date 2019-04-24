package com.qf.j1808.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private Integer userId;
    private String loginName;
    private String password;
    private String realname;
    private String state;
    private Date createTime;


}
