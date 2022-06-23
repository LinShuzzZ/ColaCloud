package org.cola.colacloud.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    @RequestMapping("/login")
    public String login(String Username,String Password,Model model)
    {
        UsernamePasswordToken userToken = new UsernamePasswordToken(Username, Password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(userToken);
        } catch (UnknownAccountException e) {
            //这个异常表示表中不存在该登录用户
            model.addAttribute("msg", "不存在该用户，请重新登录");
            return "index";
        } catch (IncorrectCredentialsException e) {
            //该异常表示该用户的登录密码不正确
            model.addAttribute("msg","密码错误");
            return "index";
        }

        return "success";
    }

}
