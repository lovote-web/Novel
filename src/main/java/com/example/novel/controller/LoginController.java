package com.example.novel.controller;

import com.example.novel.po.Status;
import com.example.novel.po.User;
import com.example.novel.service.Userservice;
import com.example.novel.utils.MD5Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆与注册
 */
@RestController
public class LoginController {

    @Autowired
    Userservice userservice;

    @PostMapping("/login")//登录
    public Map<String,Object> getUser(@RequestParam String username, @RequestParam String password){
        User user = userservice.findOne(username, MD5Encode.code(password));
        Map<String,Object> map=new HashMap<>();
        if (user == null){
            map.put("code",404);
            map.put("message","用户名或密码错误");
        }else {
            map.put("code",200);
            map.put("message","success");
            map.put("userId",user.getId());
        }
        return map;
    }

    @PostMapping("/register")//注册
    public Status register(User user){
        User user1 = userservice.findByUsername(user.getUsername());
        Status status=new Status();
        if(user1 !=null){
            status.setCode("400");
            status.setMsg("用户名已存在");
        }else {
            user.setPassword(MD5Encode.code(user.getPassword()));
            userservice.insertOne(user);
            status.setCode("200");
            status.setMsg("添加成功");
        }
        return status;
    }
}
