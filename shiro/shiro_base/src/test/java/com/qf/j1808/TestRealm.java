package com.qf.j1808;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class TestRealm {

    @Test
    public void TestRealm(){

        try {
            Factory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
            SecurityManager securityManager = (SecurityManager) factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
            subject.login(token);
            if(subject.isAuthenticated()){
                System.out.println("login success");
            }else{
                System.out.println("login failture");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }
}
