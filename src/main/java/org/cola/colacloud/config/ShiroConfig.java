package org.cola.colacloud.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> map = new HashMap<>();

        Map<String,Filter> filterMap = new HashMap<>();
		//当权限格式是e-perms时，此时会调用自定义的过滤器
        filterMap.put("e-perms", new RoleOrFilter());

        //该部分是Shiro框架给定的权限权限
        //map.put("/jump/*", "authc");

        //这是用户自定义的权限
        /*
         * 这种权限的控制的话
         * 具有update权限的管理员是无法访问query请求的
         * 
         * 因此希望实现一种方式，当用户具有query或者update请求时，就能够访问query请求
         * 简而言之，当用户具有多个权限中的一种时，就能够访问该请求
         * 可以通过自定义的过滤器来实现
         * 
        */
        map.put("/jump/add", "perms[update]");
        map.put("/jump/delete", "perms[update]");


        //e-perms[权限1,权限2,...,] e-perms中的权限满足一个即可访问
        //对于query请求，有update或者query即可访问
        map.put("/jump/query", "e-perms[update,query]");
        
        //添加自定义的过滤器
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("UserRealm") UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "UserRealm")
    public UserRealm getUserRealm()
    {
        return new UserRealm();
    }

}
