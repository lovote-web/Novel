package com.example.novel.service;

import com.example.novel.dao.BookMapper;
import com.example.novel.dao.NovelMapper;
import com.example.novel.dao.UserMapper;
import com.example.novel.po.Book;
import com.example.novel.po.Novel;
import com.example.novel.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    NovelMapper novelMapper;

    @Override
    public int joinBookshelf(Long id, Long nId) {
        User user=userMapper.getById(id);
        Novel novel=novelMapper.getNovelById(nId);
        if(user == null){
            return 2;
        }else if(novel == null){
            return 3;
        }else{
            return bookMapper.insertBook(id,nId,new Date());
        }
    }

    @Override
    public boolean isExist(Long id, Long nId) {
        boolean a=true;
        if(bookMapper.getById(id, nId)==null){
            a=false;
        }
        return a;
    }

    @Override
    public List<Book> getBookshelf(Long id) {
        return bookMapper.getNovels(id);
    }

    @Override
    public int deleteBook(Long id, Long nId) {
        return bookMapper.deleteBook(id, nId);
    }
}
