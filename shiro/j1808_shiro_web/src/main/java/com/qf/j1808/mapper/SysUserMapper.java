package com.qf.j1808.mapper;

import com.qf.j1808.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SysUserMapper {

//    根据用户名查询用户信息
    public SysUser findUSerByUsername(String loginName);


}
