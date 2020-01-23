package com.example.novel.po;

import lombok.Data;

import java.util.List;

@Data
public class Novel {
    private Long id;
    private String nName;
    private String nAnthor;
    private String description;
    private String nPhoto;
    private String url;
    private Long typeId;
    private float mark;
    private int collectionNumber;
    private Boolean isEnd;
    private List<Comment> comments;

    public Novel(){

    }
}
