package org.cola.colacloud.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.cola.colacloud.Dao.UserMapper;
import org.cola.colacloud.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //该方法用于对用户进行授权
        //该方法的调用时机：当访问需要某种权限的时候，才会去调用相应的方法
        System.out.println("用户权限");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();

        User currentUser = (User) subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //前台执行用户的登录操作的时候subject.login()会直接跳转到该方法
        System.out.println("用户登录");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //mybatis-plus的查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userToken.getUsername());

        User user = userMapper.selectOne(wrapper);
        
        if(user==null) return null;//shiro中如果该方法返回的是null，代表在表中不存在该用户，此时抛出UnknownAccountException

        //此处会自动校验用户的密码，如果密码不正确会抛出IncorrectCredentialsException
        //该方法会自动将user传递给doGetAuthorizationInfo()方法
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");

    }

    
    
}
