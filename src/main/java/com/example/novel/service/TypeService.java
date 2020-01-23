package com.example.novel.service;

import com.example.novel.po.Type;

import java.util.List;

public interface TypeService {

    public List<Type> findAll();

    public Type getOneType(Long id);
}
