package com.second.mall.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

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
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getSecurity());
        //进入后台管理的一种过滤拦截
        shiroFilterFactoryBean.setLoginUrl("/account/login");
        shiroFilterFactoryBean.setSuccessUrl("/account/index");

        // 自定义过滤器  后台管理过滤
        FormAuthenticationFilter managerAuthc = new FormAuthenticationFilter();
        //managerAuthc未登陆过用户必须登录
        managerAuthc.setLoginUrl("/account/login");
        managerAuthc.setSuccessUrl("/account/index");
        //已登录用户，记住密码后直接登录
        UserFilter managerUser = new UserFilter();
        managerUser.setLoginUrl("/account/login");

        //商城页面过滤
        FormAuthenticationFilter mallAuthc = new FormAuthenticationFilter();
        mallAuthc.setLoginUrl("/shopping/login");
        mallAuthc.setSuccessUrl("/mall/index");
        UserFilter mallUser = new UserFilter();
        mallUser.setLoginUrl("/shopping/login");

        //将自定义角色添加过滤器中
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("managerAuthc", managerAuthc);
        filters.put("managerUser", managerUser);
        filters.put("mallAuthc",mallAuthc);
        filters.put("mallUser",mallUser);
        shiroFilterFactoryBean.setFilters(filters);

        //设置访问规则   anon不需要权限  Authc现在登录 User已经登录
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/account/login", "anon");
        map.put("/account/register", "anon");
        map.put("/account/logout", "anon");
        map.put("/mall/index","anon");
        map.put("/shopping/login", "anon");
        map.put("/shopping/register", "anon");
        map.put("/mall/logout", "anon");

        //商城过滤规则  对购物车、订单、收藏页面的过滤
        map.put("/shoppingCar/enter","mallAuthc");
        map.put("/shopping/indent","mallAuthc");
        map.put("/collection/enter","mallAuthc");
        map.put(" /mall/indents","mallAuthc");

//        map.put("/shoppingCar/enter","mallUser");
//        map.put("/shopping/indent","mallUser");
//        map.put("/collection/enter","mallUser");

        //后台过滤规则
        map.put("/account/userList", "managerAuthc");
        map.put("/account/index", "managerAuthc");
//        map.put("/account/index", "managerAuthc");
//        map.put("/account/index", "managerAuthc");
//        map.put("/account/index", "managerAuthc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
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
