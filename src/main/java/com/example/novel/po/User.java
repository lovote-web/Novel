package com.example.novel.po;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private long id;
    private String username;
    private String password;

    public User(){
    }
}
