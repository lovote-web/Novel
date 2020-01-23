package com.example.novel.po;

import lombok.Data;

@Data//lombok-->@Data 相当于setter,getter 方法
public class Type {

    private Long id;
    private String typeName;
    private String typePhoto;
    private int typeCount;
    public Type(){

    }
}
