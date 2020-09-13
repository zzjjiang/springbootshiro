package com.jone.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

/**
 * @author Jone
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020年09月13日 23:01
 */
@Controller
public class UserController {


    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        //把数据存入model
        model.addAttribute("name", "黑马程序员");
        //返回test.html
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        if(StringUtils.isEmpty(username) ||
        StringUtils.isEmpty(password)){
            model.addAttribute("msg","用户名或密码不能为空");
            return "login";
        }
        //使用Shiro编写认证操作
        //1 获取subject
        Subject subject = SecurityUtils.getSubject();
        //2 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            //3 执行登陆方法 -- 具体操作在realm中
            subject.login(token);
            return "redirect:testThymeleaf";
        }catch (UnknownAccountException e){
            //登陆失败用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/noAuth";
    }
}
