package com.example.novel.po;

import lombok.Data;

import java.util.List;

@Data
public class BookList {
    private Long id;
    private String name;
    private String description;
    private String photo;
    private int size;
    private List<Novel> novels;

    public BookList(){
    }
}
