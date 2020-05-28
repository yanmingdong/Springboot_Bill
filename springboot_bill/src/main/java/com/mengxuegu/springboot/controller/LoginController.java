package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public String login(String username, String password, Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            User user = userMapper.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password))
                session.setAttribute("loginUser", user);
            //登录成功
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("HH");
            String str = df.format(date);
            int a = Integer.parseInt(str);
            if (a >= 0 && a <= 6) {
                session.setAttribute("currentTime", "凌晨好");
            }else if (a > 6 && a <= 10) {
                session.setAttribute("currentTime", "上午好");
            }else if (a > 10 && a <= 13) {
                session.setAttribute("currentTime", "中午好");
            }else if (a > 13 && a <= 18) {
                session.setAttribute("currentTime", "下午好");
            }else if (a > 18 && a <= 24) {
                session.setAttribute("currentTime", "晚上好");
            }
            System.out.println(a);
            //重定向可以重定向到任何一个请求中(包括其他项目),地址栏改变
            return "redirect:/main.html";
        }
        //登录失败
        map.put("msg", "用户名或密码错误");
        return "main/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //清空session中的用户信息
        session.removeAttribute("loginUser");
        //2.再将session注销
        session.invalidate();
        //3.返回登录页面
        return "redirect:/index.html";
    }


}
