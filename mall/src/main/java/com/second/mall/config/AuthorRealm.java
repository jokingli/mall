package com.second.mall.config;

import com.second.mall.modules.account.entity.Role;
import com.second.mall.modules.account.entity.User;
import com.second.mall.modules.account.service.RoleService;
import com.second.mall.modules.account.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yqs
 * @Comments
 * @Date 2020/12/14 11:03
 **/
@Component
public class AuthorRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登陆的用户名
        User user = (User) principals.getPrimaryPrincipal();

        List<Role> roles = roleService.getRolesByUserId(user.getUserId());
        List<String> list = new ArrayList<>();
        for (Role r : roles) {
            //获取角色表里的角色
            list.add(r.getRemark());
        }

        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        //创建权限凭证，添加角色的方法addRoles
        //添加权限的方法 addStringPermissions
        auth.addRoles(list);
        return auth;
    }

    //先认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken aToken) throws AuthenticationException {

        //1.取出用户登陆的用户名
        String userName = (String) aToken.getPrincipal();
        //2.查询用户存入数据库的密码
        User user = userService.getUserByUserName(userName);
        //3.判断用户输入是否正确
        if (user == null) {
            throw new UnknownAccountException("这个用户不存在！");
        }

        // 身份验证器，包装用户名和密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), user.getUserName());
    }

}
