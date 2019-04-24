package com.qf.j1808;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.apache.shiro.subject.Subject;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testShiroFirst(){

      Factory factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
      SecurityManager securityManager = (SecurityManager) factory.getInstance();
      SecurityUtils.setSecurityManager(securityManager);
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsans","123");
            subject.login(token);
        } catch(UnknownAccountException un){
            System.out.println("用户名或密码无效");
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if(subject.isAuthenticated()){
           System.out.println("login success");
       }else{
           System.out.println("login failture");
       }
    }
}
