package com.qf.j1808.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.subject.Subject;


@Controller
public class UserController {

//    显示登录页
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//   实现登陆后显示主页
    @RequestMapping("/deallogin")
    public String deallogin(String username,String password){

        try {
//            构造令牌对象
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//            获取主体对象
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);//是否登陆，转交控制权给SecurityManager
            if(subject.isAuthenticated()){
                return "main";
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return "unauth";
    }

    //    显示one页
    @RequestMapping("/one")
    public String one(){
        return "one";
    }

    //    显示two页
    @RequestMapping("/two")
    public String two(){
        return "two";
    }

    @RequestMapping("/logout")
    public  void logout(){

    }

    @RequestMapping("/unauth")
    public String unauth(){
        return "unauth";
    }

}
