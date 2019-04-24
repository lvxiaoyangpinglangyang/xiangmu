package com.qf.j1808.config;


import com.qf.j1808.shiro.MyShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration//标识本类为配置类
public class ShiroConfig {
    //    创建并配置shiro权限过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();//创建过滤器工厂
        filterFactoryBean.setSecurityManager(securityManager);//给过滤器配置安全管理器
//        filterFactoryBean.setLoginUrl("/login"); //设置登录访问页
        filterFactoryBean.setSuccessUrl("/main");
        filterFactoryBean.setUnauthorizedUrl("/unauth");//权限不足时跳转的页面
//        定义一个过滤的列表
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/main","authc");//登陆之后方可访问的路径
        map.put("/login","anon");//匿名访问的资源
        map.put("/dealLogin","anon");//匿名访问的资源
        map.put("/one","perms[user_edit]");//登录，且拥有user_edit权限的用户可访问
        map.put("/two","perms[user_forbidden ]");//登录，且拥有user_forbidden权限的用户可访问
        filterFactoryBean.setFilterChainDefinitionMap(map);


        return filterFactoryBean;

    }

    //创建并配置shiro安全管理器
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm){
     DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm);//给安全管理器配置自定义安全策略
        return securityManager;

    }
    //创建自定义的安全策略对象
    @Bean("myShiroRealm")//创建自定义的安全策略对象，并交给spring容器管理
    public MyShiroRealm myShiroRealm(){

        return new MyShiroRealm();
    }
}
