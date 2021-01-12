package com.second.mall.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;

/**
 * @author yqs
 * @Comments
 * @Date 2021/1/6 10:49
 **/
//注解
@Configuration
public class ShiroConfig {

    @Autowired
    private AuthorRealm authorRealm;

    //配置安全管理类
    @Bean
    public DefaultWebSecurityManager getSecurity(){
        DefaultWebSecurityManager dm = new DefaultWebSecurityManager();
        dm.setRealm(authorRealm);
        return dm;
    }

    //配置权限
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(getSecurity());
        //配置权限的map
        HashMap<String,String> map = new HashMap<>();

        //        1 配置用户访问某个页面必须登录认证访问
//        bean.setLoginUrl("/account/adminLogin");
//        //2 配置过滤器
//        //        过滤器最先运行，必须先过滤才能进入主页面  authc
//        map.put("/account/adminIndex","authc");
        //配置角色过滤器,访问/day02/manager这个连接需要角色是admin的用才能访问
        // 角色是 roles[自定义的角色参数] 权限是perms[自定义的权限参数]
        /*map.put("/day02/manager","roles[admin]");*/

//        //配置权限页面
//        bean.setUnauthorizedUrl("/warehouse/noPower");

//        //设置访问用户管理业页面需要userList权限
//        map.put("/user/list", "perms[userList]");
//        //设置访问用户管理新增页面需要userAdd权限
//        map.put("/user/add", "perms[userAdd]");
//        ////设置访问用户管理修改页面需要admin角色
//        map.put("/user/update", "roles[a]");
//        bean.setFilterChainDefinitionMap(map);

        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     * --注册shiro方言，让thymeleaf支持shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * --自动代理类，支持Shiro的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * --开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getSecurity());
        return authorizationAttributeSourceAdvisor;
    }

}
