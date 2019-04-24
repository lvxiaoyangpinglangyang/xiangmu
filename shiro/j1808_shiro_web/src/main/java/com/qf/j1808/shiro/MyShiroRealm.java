package com.qf.j1808.shiro;

import com.qf.j1808.pojo.SysPermission;
import com.qf.j1808.pojo.SysUser;
import com.qf.j1808.service.SysPermissionService;
import com.qf.j1808.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

//  授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

       Subject subject = SecurityUtils.getSubject();
       Object principal = subject.getPrincipal();//用户信息

       List<SysPermission> sysPermissions = permissionService.queryPermissionByUsername((String)principal);
       //获取用户
        Set<String> authorizedSet = new HashSet<>();
        for(SysPermission perm:sysPermissions){
            String permissionName = perm.getPermissinName();
            authorizedSet.add(permissionName);

        }
//        常见授权信息对象
      SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        给授权信息对象赋权限
       authorizationInfo.setStringPermissions(authorizedSet);
        return authorizationInfo;
    }

//  认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
         Object principal = authenticationToken.getPrincipal();//获取用户信息
         String username = (String)principal;//用户名
         SysUser sysUser = userService.queryUserByUsername(username);//根据用户名查询用户对象（含密码）

        if (sysUser!=null){
            String credentials=sysUser.getPassword();//获取查询的密码
            String realmName =this.getName();;//获取realm的名称

            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,credentials,realmName);
            return authenticationInfo;
        }
            return null;
    }
}
