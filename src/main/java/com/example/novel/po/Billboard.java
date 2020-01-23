package com.example.novel.po;

import lombok.Data;

import java.util.List;

/**
 * 榜单
 */
@Data
public class Billboard {
    private Long id;
    private String name;
    private String photo;
    private List<Novel> novels;

    public Billboard(){
    }
}
