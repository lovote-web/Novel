package com.example.novel.service;

import com.example.novel.dao.BookMapper;
import com.example.novel.dao.UserMapper;
import com.example.novel.po.Book;
import com.example.novel.po.Novel;
import com.example.novel.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserserviceImpl implements Userservice {

    @Resource
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public User findOne(String username, String password) {
        User user = userMapper.getByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User insertOne(User user) {
        userMapper.insertOne(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userMapper.getByUsername(username);
        return user;
    }


}
