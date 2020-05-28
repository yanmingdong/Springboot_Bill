package com.mengxuegu.springboot.controller;

import com.mengxuegu.springboot.entities.Bill;
import com.mengxuegu.springboot.entities.Provider;
import com.mengxuegu.springboot.entities.User;
import com.mengxuegu.springboot.mapper.BillMapper;
import com.mengxuegu.springboot.mapper.ProviderMapper;
import com.mengxuegu.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/users")
    public String list(Map<String, Object> map, User user) {
        List<User> users = userMapper.getUsers(user);

        map.put("users", users);
        map.put("username", user.getUsername());

        return "user/list";
    }

    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id, Map<String, Object> map,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        User user = userMapper.getUserById(id);

        map.put("user", user);
        //如果type=null,进入view页面,否则进入update
        return "user/" + type;
    }

    @PutMapping("/user")
    public String update(User user) {
        System.out.println("后台执行了");
        userMapper.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String toAddPage() {
        return "user/add";
    }

    @PostMapping("/user")
    public String add(User user) {
        userMapper.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userMapper.deleteUser(id);
        return "redirect:/users";
    }

    /**
     * 进入修改密码页面
     * @return
     */
    @GetMapping("/user/pwd")
    public String toUpdatePassword(){
        return "main/password";
    }

    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPassword(@PathVariable("oldPwd") String oldPwd, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        if(user.getPassword().equals(oldPwd)){
            return true;
        }
        return false;
    }

    @PostMapping("/user/pwd")
    public String updatePassword(HttpSession session,String password){
        User user = (User)session.getAttribute("loginUser");
        user.setPassword(password);
        userMapper.updateUser(user);
        return "redirect:/logout";
    }

}
