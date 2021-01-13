package com.second.mall.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
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
import java.util.HashMap;
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
        shiroFilterFactoryBean.setLoginUrl("/account/login");
        shiroFilterFactoryBean.setSuccessUrl("/account/index");

        // 自定义过滤器
        FormAuthenticationFilter managerAuthc = new FormAuthenticationFilter();
        managerAuthc.setLoginUrl("/account/login");
        managerAuthc.setSuccessUrl("/account/index");
        UserFilter managerUser = new UserFilter();
        managerUser.setLoginUrl("/account/login");

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("managerAuthc", managerAuthc);
        filters.put("managerUser", managerUser);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/account/login", "anon");
        map.put("/account/register", "anon");
        map.put("/account/logout", "anon");

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
