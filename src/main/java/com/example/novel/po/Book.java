package com.example.novel.po;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    Novel novel;
    private Date createTime;

    public Book(){
    }
}
