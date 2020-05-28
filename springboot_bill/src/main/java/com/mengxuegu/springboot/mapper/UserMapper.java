package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.entities.Bill;
import com.mengxuegu.springboot.entities.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    //登录
    User getUserByUsername(String username);

    List<User> getUsers(User user);

    User getUserById(Integer id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);

}
