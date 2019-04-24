package com.qf.j1808;

import com.qf.j1808.mapper.SysPermissionMapper;
import com.qf.j1808.mapper.SysUserMapper;
import com.qf.j1808.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class J1808ShiroWebApplicationTests {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void Test2(){
        SysUser sysUsers = sysUserMapper.findUSerByUsername("admin");
        System.out.println(sysUsers);
    }

    @Test
    public void Test1(){
        SysUser sysUser = sysUserMapper.findUSerByUsername("test2");
        System.out.println(sysUser);
    }

}
