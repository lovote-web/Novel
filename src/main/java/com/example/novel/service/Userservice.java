package com.example.novel.service;


import com.example.novel.po.Book;
import com.example.novel.po.Novel;
import com.example.novel.po.User;

import java.util.List;

public interface Userservice {

    public User findOne(String username, String password);

    public User insertOne(User user);

    public User findByUsername(String username);

}
