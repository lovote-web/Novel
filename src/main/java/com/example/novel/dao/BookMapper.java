package com.example.novel.dao;

import com.example.novel.po.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> getNovels(@Param("id") Long id);

    int insertBook(@Param("id") Long id, @Param("nId") Long nId,@Param("date") Date date);

    @Select("select * from bookshelf where id=#{id} and novel_id=#{nId}")
    Book getById(@Param("id")Long id,@Param("nId") Long nId);

    int deleteBook(@Param("id") Long id,@Param("nId") Long nId);

}
