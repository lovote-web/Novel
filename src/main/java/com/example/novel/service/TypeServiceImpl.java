package com.example.novel.service;

import com.example.novel.dao.TypeMapper;
import com.example.novel.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Resource
    TypeMapper typeMapper;

    @Override
    public List<Type> findAll() {
        return typeMapper.findAll();
    }

    @Override
    public Type getOneType(Long id) {
        return typeMapper.getTypeById(id);
    }
}
