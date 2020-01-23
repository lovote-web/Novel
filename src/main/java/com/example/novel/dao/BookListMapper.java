package com.example.novel.dao;

import com.example.novel.po.BookList;
import com.example.novel.po.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookListMapper {

    @Select("select * from booklist")
    public List<BookList> getAllBookList();

    public BookList getOneBookList(Long id);

    @Select("select n.* from novel n,booklist_novel t where t.id=#{id} and t.novel_id=n.id")
    public List<Novel> getNovelsInBookList(Long id);

    @Select("select count(*) from booklist_novel where id=#{id}")
    public int getBookListSize(Long id);
}
