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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        User user = (User) principals.getPrimaryPrincipal();
        if (user == null) {
            throw new UnknownAccountException("用户名不存在");
        }
        List<Role> roles = new ArrayList<Role>();
        if (user.getUserName().equals("admin")) {
            roles = roleService.getRoles();
            if (roles.isEmpty()) {
                simpleAuthorizationInfo.addRole("admin");
            }
        } else {
            roles = roleService.getRolesByUserId(user.getUserId());
            System.err.println("授权成功"+roles);
        }
        for (Role role :roles) {
            simpleAuthorizationInfo.addRole(role.getRemark());
            System.err.println("授权成功"+roles);
        }
        return simpleAuthorizationInfo;
    }

    //先认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken aToken) throws AuthenticationException {

        //1.取出用户登陆的用户名
        String userName = (String) aToken.getPrincipal();
        //2.查询用户存入数据库的密码
        User user = userService.getUserByUserName(userName);
//        HttpServletRequest request=ServletActionContext.getRequest();
//        request.getSession().setAttribute("userId",user.getUserId());
        //3.判断用户输入是否正确
        if (user == null) {
            throw new UnknownAccountException("这个用户不存在！");
        }

        // 身份验证器，包装用户名和密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), user.getUserName());
    }

}
