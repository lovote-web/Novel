package com.example.novel.po;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Long id;
    private String content;
    private Date createTime;
    private Long novelId;
    private int goods = 0;
    private Long parentCommentId;

    public Comment(){
    }
}