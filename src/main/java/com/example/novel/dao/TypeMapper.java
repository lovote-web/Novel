package com.example.novel.dao;

import com.example.novel.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TypeMapper {

    @Select("select * from type")
    public List<Type> findAll();

    @Select("select * from type where id=#{id}")
    public Type getTypeById(Long id);
}