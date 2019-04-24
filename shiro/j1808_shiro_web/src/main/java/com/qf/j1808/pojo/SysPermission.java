package com.qf.j1808.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {

    private Integer permissinId;//权限
    private String permissinName;//权限名称
    private String menuName;//菜单名
    private String menuUrl;//菜单url
    private String menuLevel;//菜单级别
    private String menuCode;//编码
    private Integer parentCode;//父菜单编码
    private Integer ifValid;//是否有效

}
