package org.cola.colacloud.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RoleOrFilter extends AuthorizationFilter
{
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        System.out.println("调用自定义的权限过滤");

        /*
         * 用于测试git提交的注释
         * 注释时间 16:00
         * 
        */

        Subject subject = this.getSubject(request, response);
        
        String[] perms = (String[]) mappedValue;

        for (String perm : perms) {
            if(subject.isPermitted(perm))
                return true;
        }
        return false;
    }    

}
