package com.qf.j1808;


import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestPermission {

    @Test
    public void testPerssion(){

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();

       UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
       subject.login(token);
       if(subject.isAuthenticated()){
           System.out.println("login success");
          Boolean permitted = subject.isPermitted("user:add");
           System.out.println("zhangsan owner:"+permitted);
          boolean s = subject.hasRole("role2");
           System.out.println("zhangsan ownered roler2:"+s);


       }

    }
}
