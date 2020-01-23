package com.example.novel.dao;

import com.example.novel.po.Book;
import com.example.novel.po.Novel;
import com.example.novel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

     User getByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

     //注册
     int insertOne(@Param("user") User user);

     //通过username查询
     User getByUsername(@Param("username") String username);

     @Select("select * from user where id=#{id}")
     User getById(Long id);
}
