package com.jone.springbootshiro.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jone.springbootshiro.shiro.CustomRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jone
 * @version 1.0.0
 * @Description 过滤器顺序相当重要
 * @createTime 2020年09月13日 21:04
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建shiroFiterFactory 对象
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 配置内置过滤器
         * anon：无需认证登陆，可以访问
         * authc：必须认证才可以访问
         * user：如果使用rememberMe的功能可以直接访问
         * perms：该资源必须得到资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        //普通写法  拦截之后，会跳转到login.jsp页面
//        filterMap.put("/add","authc");
//        filterMap.put("/update","authc");
        filterMap.put("/testThymeleaf", "anon");
        filterMap.put("/login", "anon");

        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/add", "perms[user:add]");
        // 通配
        filterMap.put("/*","authc");

        //指定拦截未认证请求的，跳转链接
        shiroFilterFactoryBean.setLoginUrl("/toLogin");


        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
